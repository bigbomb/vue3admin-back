package com.loafer.springboot.modules.base.quartz.utils;

import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.common.utils.SpringContext;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskLogEntity;
import com.loafer.springboot.modules.base.quartz.service.BaseScheduleTaskLogService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;

@DisallowConcurrentExecution // 上次任务没有执行完，下次任务推迟执行
public class QuartzJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BaseScheduleTaskEntity taskEntity = (BaseScheduleTaskEntity) jobExecutionContext
                .getMergedJobDataMap()
                .get(BaseScheduleTaskEntity.TASK_PARAM_KEY);
        logger.info("定时器参数，{}",taskEntity);

        // 获取日志服务对象Bean
        BaseScheduleTaskLogService taskLogService = (BaseScheduleTaskLogService)SpringContext
                .getBean("baseScheduleTaskLogService");
        // 数据库保存执行记录
        BaseScheduleTaskLogEntity taskLogEntity = new BaseScheduleTaskLogEntity();
        taskLogEntity.setTaskId(taskEntity.getId());
        taskLogEntity.setBeanName(taskEntity.getBeanName());
        taskLogEntity.setParams(taskEntity.getParams());
        taskLogEntity.setCreatedAt(new Date());

        // 任务开始执行时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
            logger.debug("准备执行任务：" + taskEntity.getId());

            Object target = SpringContext.getBean(taskEntity.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            method.invoke(target, taskEntity.getParams());

            // 任务执行时长
            long times = System.currentTimeMillis() - startTime;

            String message = "任务执行完毕，任务ID：" + taskEntity.getId() + "  总共耗时：" + times + "毫秒";

            // 任务状态 0：失败 1：成功
            taskLogEntity.setTimes((int) times);
            taskLogEntity.setStatus(1);
            taskLogEntity.setMessage(message);
            logger.debug(message);
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + taskEntity.getId(), e);
            // 任务执行时长
            long times = System.currentTimeMillis() - startTime;

            // 任务状态 0：失败 1：成功
            taskLogEntity.setTimes((int)times);
            taskLogEntity.setStatus(0);
            taskLogEntity.setError(e.toString().substring(0,2000));
        }finally {
            // 最终记录到数据库
            taskLogService.save(taskLogEntity);
        }
    }
}
