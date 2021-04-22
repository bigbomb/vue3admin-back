package com.loafer.springboot.modules.base.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface BaseScheduleTaskLogService extends IService<BaseScheduleTaskLogEntity> {

    /**
     * 分页列表
     * @param params 分页参数
     * @return RPage
     */
    RPage<BaseScheduleTaskLogEntity> queryPage(Map<String, Object> params);

}
