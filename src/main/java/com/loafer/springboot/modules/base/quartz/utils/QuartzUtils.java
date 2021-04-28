package com.loafer.springboot.modules.base.quartz.utils;

import com.loafer.springboot.common.exception.RunException;
import com.loafer.springboot.common.utils.Constant;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;
import org.quartz.*;

import java.util.Date;

/**
 * 定时任务工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class QuartzUtils {
    // 前缀
    private static final String JOB_NAME = "TASK_";

    /**
     * 获取 jobKey
     * @param taskId 任务ID
     * @return JobKey
     */
    public static JobKey getJobKey(Long taskId){
        return JobKey.jobKey(JOB_NAME + taskId);
    }

    /**
     * 获取 triggerKey
     * @param taskId 任务ID
     * @return triggerKey
     */
    public static TriggerKey getTriggerKey(Long taskId){
        return TriggerKey.triggerKey(JOB_NAME + taskId);
    }

    /**
     * 获取表达式触发器
     * @param scheduler
     * @param taskId 任务ID
     * @return 表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long taskId){
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(taskId));
        } catch (SchedulerException e) {
            throw new RunException("获取定时任务CronTrigger出现异常!", e);
        }
    }

    /**
     * 创建任务
     * @param taskEntity 任务实体类（对应自定义的数据库任务表）
     * @return Date 返回创建任务成功后执行时间
     */
    public static Date createScheduleTask(Scheduler scheduler, BaseScheduleTaskEntity taskEntity){
        try {
            // 1.定义 jobDetail(构建job信息)
            JobDetail jobDetail = JobBuilder
                    .newJob(QuartzJob.class)
                    .withIdentity(getJobKey(taskEntity.getId()))
                    .build();
            // 2.定义trigger（按新的cronExpression表达式构建一个新的trigger）
            // 不触发立即执行，等待下次Cron触发频率到达时刻开始按照Cron频率依次执行  withMisfireHandlingInstructionDoNothing
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(taskEntity.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(getTriggerKey(taskEntity.getId()))
                    .withSchedule(cronScheduleBuilder)
                    .build();
            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(taskEntity.TASK_PARAM_KEY, taskEntity);
            // 3.执行
            Date startTime = scheduler.scheduleJob(jobDetail,trigger);
            // 暂停任务 0表示暂停，1表示继续
            if (taskEntity.getStatus() == Constant.ScheduleTaskStatus.PAUSE.getValue()){
                pauseScheduleTask(scheduler, taskEntity.getId());
            }
            return startTime;
        } catch (SchedulerException e) {
            throw new RunException("创建定时任务失败!", e);
        }
    }

    /**
     * 更新定时任务
     * @param taskEntity 任务实体类（对应自定义的数据库任务表）
     * @return Date 返回更新任务成功后执行时间
     */
    public static Date updateScheduleTask(Scheduler scheduler, BaseScheduleTaskEntity taskEntity){
        try {
            TriggerKey triggerKey = getTriggerKey(taskEntity.getId());
            // 1.获取 cron 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(taskEntity.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = getCronTrigger(scheduler, taskEntity.getId());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger
                    .getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();
            // 放入参数，运行时的方法可以获取
            trigger.getJobDataMap().put(taskEntity.TASK_PARAM_KEY, taskEntity);
            // 2.执行
            Date updateTime = scheduler.rescheduleJob(triggerKey,trigger);
            // 暂停任务 0表示暂停，1表示继续
            if (taskEntity.getStatus() == Constant.ScheduleTaskStatus.PAUSE.getValue()){
                pauseScheduleTask(scheduler, taskEntity.getId());
            }
            return updateTime;
        } catch (SchedulerException e) {
            throw new RunException("更新定时任务失败!", e);
        }
    }

    /**
     * 立即执行（让定时任务立即执行，**注意：暂停状态下的定时任务，如果立即执行，只会执行一次，相当于一次性执行）
     * @param taskEntity 任务实体类（对应自定义的数据库任务表）
     */
    public static void runScheduleTask(Scheduler scheduler, BaseScheduleTaskEntity taskEntity){
        try {
            // 参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(taskEntity.TASK_PARAM_KEY, taskEntity);

            scheduler.triggerJob(getJobKey(taskEntity.getId()), dataMap);
        } catch (SchedulerException e) {
            throw new RunException("立即执行定时任务失败!", e);
        }
    }

    /**
     * 暂停任务
     * @param jobId 任务jobId
     */
    public static void pauseScheduleTask(Scheduler scheduler, Long jobId){
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RunException("暂停定时任务失败!", e);
        }
    }

    /**
     * 恢复任务
     * @param taskId 任务jobId
     */
    public static void resumeScheduleTask(Scheduler scheduler, Long taskId){
        try {
            scheduler.resumeJob(getJobKey(taskId));
        } catch (SchedulerException e) {
            throw new RunException("恢复定时任务失败!", e);
        }
    }

    /**
     * 删除定时任务
     * @param taskId 定时任务id
     */
    public static void deleteScheduleTask(Scheduler scheduler, Long taskId){
        try {
            // 1.暂停触发器
            scheduler.pauseTrigger(getTriggerKey(taskId));
            // 2.删除触发器
            scheduler.unscheduleJob(getTriggerKey(taskId));
            // 3.删除任务
            scheduler.deleteJob(getJobKey(taskId));
        } catch (SchedulerException e) {
            throw new RunException("删除定时任务失败!", e);
        }
    }

    /**
     * 验证定时任务是否存在
     * @param taskId 任务id
     * @return true：存在 false:不存在
     */
    public static boolean checkScheduleTask(Scheduler scheduler, Long taskId){
        try {
            return scheduler.checkExists(getJobKey(taskId));
        } catch (SchedulerException e) {
            throw new RunException("验证定时任务是否存在失败!", e);
        }
    }

}
