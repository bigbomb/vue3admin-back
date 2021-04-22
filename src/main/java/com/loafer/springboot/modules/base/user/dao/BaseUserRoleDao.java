package com.loafer.springboot.modules.base.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loafer.springboot.modules.base.user.entity.BaseUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Mapper
public interface BaseUserRoleDao extends BaseMapper<BaseUserRoleEntity> {
	
}
