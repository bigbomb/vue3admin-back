package com.loafer.springboot.modules.base.menu.service.impl;

import com.loafer.springboot.common.utils.Common;
import com.loafer.springboot.common.utils.Constant;
import com.loafer.springboot.modules.base.menu.dao.BaseMenuDao;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuDto;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuSelectDto;
import com.loafer.springboot.modules.base.menu.entity.BaseMenuEntity;
import com.loafer.springboot.modules.base.menu.service.BaseMenuService;
import com.loafer.springboot.modules.base.role.dto.BaseRoleDto;
import com.loafer.springboot.modules.base.role.entity.BaseRoleEntity;
import com.loafer.springboot.modules.base.role.service.BaseRoleMenuService;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.service.BaseUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("baseMenuService")
public class BaseMenuServiceImpl extends ServiceImpl<BaseMenuDao, BaseMenuEntity> implements BaseMenuService {

    @Resource
    private BaseRoleMenuService baseRoleMenuService;
    @Resource
    private BaseUserService baseUserService;

    @Override
    public Set<String> queryPermission(Long userId) {
        List<String> permissions;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(userId);
        if (isSuper) { // 是超级管理员
            permissions = baseMapper.queryAllPermission();
        } else { // 非超级管理员
            permissions = baseMapper.queryPermissionByUserId(userId);
        }
        return getPermissionSet(permissions);
    }

    @Override
    public List<BaseMenuDto> queryMenu(Long userId) {
        List<Long> menuIds;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(userId);
        if (isSuper) { // 是超级管理员
            // 获取所有的菜单权限ID
            menuIds = baseMapper.queryAllMenuId();
        } else { // 非超级管理员
            // 获取当前用户所有的角色ID
            menuIds = getUserRoleId(userId);
        }
        // 获取根目录菜单权限
        List<BaseMenuDto> menus = baseMapper.queryMenuByParentId(0L, menuIds);
        return queryMenuTree(menus, menuIds);
    }

    @Override
    public List<BaseMenuDto> queryAllMenuByParentId(Long parentId) {
        if (parentId == null) {
            parentId = 0L;
        }
        return baseMapper.queryAllMenuByParentId(parentId);
    }

    @Override
    public List<BaseMenuSelectDto> queryNoButtonMenu() {
        List<Integer> types = new ArrayList<>();
        types.add(Constant.MenuType.CATALOG.getValue());
        types.add(Constant.MenuType.MENU.getValue());
        return baseMapper.queryAllMenu(types, null, null);
    }

    @Override
    public List<BaseMenuSelectDto> queryAllMenuByRole(Long userId) {
        List<Long> menuIds;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(userId);
        if (isSuper) {
            menuIds = null;
        } else {
            // 获取当前用户所有的角色ID
            menuIds = getUserRoleId(userId);
        }
        return baseMapper.queryAllMenu(null, menuIds, null);
    }

    @Override
    public BaseMenuDto queryById(Long id) {
        return baseMapper.queryById(id);
    }

    @Override
    public void create(BaseMenuEntity baseMenuEntity) {
        baseMenuEntity.setCreatedAt(new Date());
        this.save(baseMenuEntity);
    }

    @Override
    public void update(BaseMenuEntity baseMenuEntity) {
        baseMenuEntity.setUpdatedAt(new Date());
        this.updateById(baseMenuEntity);
    }

    /**
     * 递归获取权限菜单
     * @param menus 父级菜单
     * @param menuIds 拥有的菜单ID
     * @return
     */
    private List<BaseMenuDto> queryMenuTree(List<BaseMenuDto> menus, List<Long> menuIds) {
        List<BaseMenuDto> list = new ArrayList<>();
        if (menuIds == null || menuIds.size() == 0) {
            return list;
        }
        for (BaseMenuDto menu : menus) {
            if (!menuIds.contains(menu.getId())) {
                break;
            }
            if (menu.getType() != Constant.MenuType.BUTTON.getValue()) {
                menu.setChildren(baseMapper.queryMenuByParentId(menu.getId(), menuIds));
            }
            list.add(menu);
        }
        return list;
    }

    /**
     * 权限list 转 set
     * @param permissionList
     * @return
     */
    private Set<String> getPermissionSet(List<String> permissionList) {
        Set<String> permissions = new HashSet<>();
        for (String p : permissionList) {
            if (StringUtils.isBlank(p)) {
                continue;
            }
            permissions.addAll(Arrays.asList(p.trim().split(";")));
        }
        return permissions;
    }

    /**
     * 获取用户角色ID
     * @param userId 用户ID
     * @return
     */
    private List<Long> getUserRoleId(Long userId) {
        BaseUserDto baseUserDto = baseUserService.queryById(userId);
        List<Long> roleIds = new ArrayList<>();
        for (BaseRoleDto role : baseUserDto.getRoles()) {
            roleIds.add(role.getId());
        }
        // 通过角色ID获取拥有的菜单权限ID
        if (roleIds.size() == 0) {
            return null;
        } else {
            return baseRoleMenuService.queryMenuIdByRoleId(roleIds);
        }
    }
}