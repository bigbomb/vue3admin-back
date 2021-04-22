package com.loafer.springboot.modules.base.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loafer.springboot.modules.base.role.entity.BaseRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 角色菜单关联
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface BaseRoleMenuDao extends BaseMapper<BaseRoleMenuEntity> {

    /**
     * 根据角色ID获取用户菜单ID
     * @param roleIds
     * @return
     */
    List<Long> queryMenuIdByRoleId(@Param("roleIds") List<Long> roleIds);

}
