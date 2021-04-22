package com.loafer.springboot.modules.base.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.entity.BaseUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Mapper
public interface BaseUserDao extends BaseMapper<BaseUserEntity> {

    IPage<BaseUserDto> queryAllPage(Page<BaseUserDto> page);

    IPage<BaseUserDto> queryByCreatorPage(Page<BaseUserDto> page, @Param("Creator") Long creator);

    BaseUserDto queryById (@Param("id") Long id);

    BaseUserDto queryByUsername (@Param("username") String username);
	
}
