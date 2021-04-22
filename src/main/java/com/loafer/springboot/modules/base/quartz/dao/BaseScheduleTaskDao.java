package com.loafer.springboot.modules.base.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.entity.BaseUserEntity;
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

	
}
