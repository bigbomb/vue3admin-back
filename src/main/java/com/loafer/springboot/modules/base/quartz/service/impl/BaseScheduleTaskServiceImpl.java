package com.loafer.springboot.modules.base.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.common.utils.Constant;
import com.loafer.springboot.common.utils.Query;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.quartz.dao.BaseScheduleTaskDao;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;
import com.loafer.springboot.modules.base.quartz.service.BaseScheduleTaskService;
import com.loafer.springboot.modules.base.quartz.utils.QuartzUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("baseScheduleTaskService")
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
        Page<BaseScheduleTaskEntity> page = new Query<BaseScheduleTaskEntity>().getPage(params);
        String beanName = (String)params.get("bean_name");

        QueryWrapper<BaseScheduleTaskEntity> wrapper = new QueryWrapper<BaseScheduleTaskEntity>()
                .eq(StringUtils.isNotBlank(beanName), "bean_name", beanName);

        return new RPage<>(this.page(page, wrapper));
    }

    @Override
    @Transactional
    public void create(BaseScheduleTaskEntity baseScheduleTaskEntity) {
        baseScheduleTaskEntity.setCreatedAt(new Date());
        baseScheduleTaskEntity.setStatus(Constant.ScheduleTaskStatus.NORMAL.getValue());

        this.save(baseScheduleTaskEntity);

        QuartzUtils.createScheduleTask(scheduler, baseScheduleTaskEntity);
    }

    @Override
    public void update(BaseScheduleTaskEntity baseScheduleTaskEntity) {
        QuartzUtils.updateScheduleTask(scheduler, baseScheduleTaskEntity);

        this.updateById(baseScheduleTaskEntity);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        for(Long id : ids){
            QuartzUtils.deleteScheduleTask(scheduler, id);
        }

        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public void runBatch(Long[] ids) {
        for(Long id : ids){
            QuartzUtils.runScheduleTask(scheduler, getById(id));
        }
    }

    @Override
    public void resumeBatch(Long[] ids) {
        for(Long id : ids){
            QuartzUtils.resumeScheduleTask(scheduler, id);
        }

        baseMapper.updateBatch(ids, Constant.ScheduleTaskStatus.NORMAL.getValue());
    }

    @Override
    public void pauseBatch(Long[] ids) {
        for(Long id : ids){
            QuartzUtils.pauseScheduleTask(scheduler, id);
        }

        baseMapper.updateBatch(ids, Constant.ScheduleTaskStatus.PAUSE.getValue());
    }
}
