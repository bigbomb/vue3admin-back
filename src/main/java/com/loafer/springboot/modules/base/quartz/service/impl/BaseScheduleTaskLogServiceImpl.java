package com.loafer.springboot.modules.base.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.common.utils.Query;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.quartz.dao.BaseScheduleTaskLogDao;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskLogEntity;
import com.loafer.springboot.modules.base.quartz.service.BaseScheduleTaskLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("baseScheduleTaskLogService")
public class BaseScheduleTaskLogServiceImpl extends ServiceImpl<BaseScheduleTaskLogDao, BaseScheduleTaskLogEntity> implements BaseScheduleTaskLogService {

    @Override
    public RPage<BaseScheduleTaskLogEntity> queryPage(Map<String, Object> params) {
        Page<BaseScheduleTaskLogEntity> page = new Query<BaseScheduleTaskLogEntity>().getPage(params);
        String beanName = (String)params.get("bean_name");

        QueryWrapper<BaseScheduleTaskLogEntity> wrapper = new QueryWrapper<BaseScheduleTaskLogEntity>()
                .like(StringUtils.isNotBlank(beanName), "bean_name", beanName)
                .orderByDesc("created_at");

        return new RPage<>(this.page(page, wrapper));
    }
}
