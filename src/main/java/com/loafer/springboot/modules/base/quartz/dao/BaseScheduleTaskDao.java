package com.loafer.springboot.modules.base.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface BaseScheduleTaskDao extends BaseMapper<BaseScheduleTaskEntity> {

	void updateBatch(@Param("ids") Long[] ids, @Param("status") Integer status);
}
