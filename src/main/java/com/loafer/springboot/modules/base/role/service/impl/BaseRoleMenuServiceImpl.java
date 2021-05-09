package com.loafer.springboot.modules.base.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.modules.base.role.dao.BaseRoleMenuDao;
import com.loafer.springboot.modules.base.role.entity.BaseRoleMenuEntity;
import com.loafer.springboot.modules.base.role.service.BaseRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色菜单关联
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("baseRoleMenuService")
public class BaseRoleMenuServiceImpl extends ServiceImpl<BaseRoleMenuDao, BaseRoleMenuEntity> implements BaseRoleMenuService {


    @Override
    public List<Long> queryMenuIdByRoleId(List<Long> roleIds) {

        return this.baseMapper.queryMenuIdByRoleId(roleIds);
    }

    @Override
    public void createOrUpdate(Long roleId, List<Long> menuIds) {
        QueryWrapper<BaseRoleMenuEntity> wrapper = new QueryWrapper<BaseRoleMenuEntity>()
                .eq("role_id", roleId);
        this.remove(wrapper);

        if (menuIds.size() == 0) {
            return;
        }

        List<BaseRoleMenuEntity> baseRoleMenuEntities = new ArrayList<>();
        for (Long menuId : menuIds) {
            BaseRoleMenuEntity roleMenu = new BaseRoleMenuEntity();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            baseRoleMenuEntities.add(roleMenu);
        }

        this.saveBatch(baseRoleMenuEntities);
    }
}