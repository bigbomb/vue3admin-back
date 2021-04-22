package com.loafer.springboot.modules.base.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.quartz.dao.BaseScheduleTaskDao;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;
import com.loafer.springboot.modules.base.quartz.service.BaseScheduleTaskService;
import com.loafer.springboot.modules.base.quartz.utils.QuartzUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class BaseScheduleTaskServiceImpl extends ServiceImpl<BaseScheduleTaskDao, BaseScheduleTaskEntity> implements BaseScheduleTaskService {

    @Resource
    private Scheduler scheduler;

    /**
     * 启动时创建定时任务，如果已存在则更新定任务
     */
    @PostConstruct // 服务启动时候加载且只执行一次
    public void init() {
        List<BaseScheduleTaskEntity> tasks = this.list();
        for(BaseScheduleTaskEntity task : tasks) {
            CronTrigger cronTrigger = QuartzUtils.getCronTrigger(scheduler, task.getId());
            if (cronTrigger == null) {
                QuartzUtils.createScheduleTask(scheduler, task);
            } else {
                QuartzUtils.updateScheduleTask(scheduler, task);
            }
        }
    }

    @Override
    public RPage<BaseScheduleTaskEntity> queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void create(BaseScheduleTaskEntity baseScheduleTaskEntity) {

    }

    @Override
    public void update(BaseScheduleTaskEntity baseScheduleTaskEntity) {

    }

    @Override
    public void updateBatch(Long ids, int status) {

    }

    @Override
    public void deleteBatch(Long ids) {

    }

    @Override
    public void runBatch(Long ids) {

    }

    @Override
    public void resumeBatch(Long ids) {

    }

    @Override
    public void pauseBatch(Long ids) {

    }
}
