package com.loafer.springboot.modules.base.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.modules.base.role.entity.BaseRoleMenuEntity;
import com.loafer.springboot.modules.base.user.dao.BaseUserRoleDao;
import com.loafer.springboot.modules.base.user.entity.BaseUserRoleEntity;
import com.loafer.springboot.modules.base.user.service.BaseUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("baseUserRoleService")
public class BaseUserRoleServiceImpl extends ServiceImpl<BaseUserRoleDao, BaseUserRoleEntity> implements BaseUserRoleService {

    @Override
    @Transactional
    public void createOrUpdate(Long userId, List<Long> roleIds) {
        QueryWrapper<BaseUserRoleEntity> wrapper = new QueryWrapper<BaseUserRoleEntity>()
                .eq("user_id", userId);
        this.remove(wrapper);

        if (roleIds.size() == 0) {
            return;
        }

        List<BaseUserRoleEntity> baseUserRoleEntities = new ArrayList<>();
        for (Long roleId : roleIds) {
            BaseUserRoleEntity userRole = new BaseUserRoleEntity();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            baseUserRoleEntities.add(userRole);
        }

        this.saveBatch(baseUserRoleEntities);
    }
}