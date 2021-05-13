package com.loafer.springboot.modules.base.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface BaseScheduleTaskService extends IService<BaseScheduleTaskEntity> {

    /**
     * 分页列表
     * @param params 分页参数
     * @return RPage
     */
    RPage<BaseScheduleTaskEntity> queryPage(Map<String, Object> params);

    /**
     * 创建
     * @param baseScheduleTaskEntity 实体
     */
    void create(BaseScheduleTaskEntity baseScheduleTaskEntity);

    /**
     * 更新
     * @param baseScheduleTaskEntity 实体
     */
    void update(BaseScheduleTaskEntity baseScheduleTaskEntity);

    /**
     * 批量删除
     * @param ids 实体id
     */
    void deleteBatch(Long[] ids);

    /**
     * 批量执行
     * @param ids 实体id
     */
    void runBatch(Long[] ids);

    /**
     * 批量恢复
     * @param ids 实体id
     */
    void resumeBatch(Long[] ids);

    /**
     * 批量暂停
     * @param ids 实体id
     */
    void pauseBatch(Long[] ids);

}
