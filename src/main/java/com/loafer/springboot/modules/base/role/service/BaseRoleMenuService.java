package com.loafer.springboot.modules.base.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuDto;
import com.loafer.springboot.modules.base.role.entity.BaseRoleMenuEntity;

import java.util.List;
import java.util.Set;

/**
 * 角色菜单关联
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface BaseRoleMenuService extends IService<BaseRoleMenuEntity> {

    /**
     * 根据角色ID获取用户菜单ID
     * @param roleIds
     * @return
     */
    List<Long> queryMenuIdByRoleId(List<Long> roleIds);

    /**
     * 角色菜单关联新增、编辑
     * @param roleId
     * @param menuIds
     */
    void createOrUpdate(Long roleId, List<Long> menuIds);

}

