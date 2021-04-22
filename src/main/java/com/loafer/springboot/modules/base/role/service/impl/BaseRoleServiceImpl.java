package com.loafer.springboot.modules.base.role.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.common.exception.RunException;
import com.loafer.springboot.common.utils.Common;
import com.loafer.springboot.common.utils.Query;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.role.dao.BaseRoleDao;
import com.loafer.springboot.modules.base.role.dto.BaseRoleDto;
import com.loafer.springboot.modules.base.role.dto.BaseRoleSelectDto;
import com.loafer.springboot.modules.base.role.entity.BaseRoleEntity;
import com.loafer.springboot.modules.base.role.service.BaseRoleMenuService;
import com.loafer.springboot.modules.base.role.service.BaseRoleService;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("baseRoleService")
public class BaseRoleServiceImpl extends ServiceImpl<BaseRoleDao, BaseRoleEntity> implements BaseRoleService {

    @Resource
    private BaseRoleMenuService baseRoleMenuService;

    @Override
    public RPage<BaseRoleDto> queryPage(Map<String, Object> params) {
        Page<BaseRoleDto> page = new Query<BaseRoleDto>().getPage(params);
        Long userId = (Long)params.get("userId");
        String name = (String)params.get("name");
        RPage<BaseRoleDto> rPage;
        boolean isSuper = Common.isSuper(userId);
        if (isSuper) {
            rPage = new RPage<>(baseMapper.queryPage(page, null, name, null));
        } else {
            rPage = new RPage<>(baseMapper.queryPage(page, userId, name, 1));
        }
        return rPage;
    }

    @Override
    public BaseRoleDto queryById(Long id, Long userId) {
        BaseRoleDto baseRoleDto = baseMapper.queryById(id);
        boolean isSuper = Common.isSuper(userId);
        if (isSuper) {
            return baseRoleDto;
        } else {
            return userId.equals(baseRoleDto.getCreator()) ? baseRoleDto : null;
        }
    }

    @Override
    @Transactional
    public void create(BaseRoleEntity baseRoleEntity, BaseUserDto baseUserDto) {
        validatedPermission(baseRoleEntity, baseUserDto);

        baseRoleEntity.setCreator(baseUserDto.getId());
        baseRoleEntity.setCreatedAt(new Date());
        this.save(baseRoleEntity);

        baseRoleMenuService.createOrUpdate(baseRoleEntity.getId(), baseRoleEntity.getMenuIds());
    }

    @Override
    @Transactional
    public void update(BaseRoleEntity baseRoleEntity, BaseUserDto baseUserDto) {
        validatedPermission(baseRoleEntity, baseUserDto);

        baseRoleEntity.setUpdater(baseUserDto.getId());
        baseRoleEntity.setUpdatedAt(new Date());
        this.updateById(baseRoleEntity);

        baseRoleMenuService.createOrUpdate(baseRoleEntity.getId(), baseRoleEntity.getMenuIds());
    }

    @Override
    public List<BaseRoleSelectDto> querySelect(Long userId) {
        Integer isDisplay;
        Long creator;
        boolean isSuper = Common.isSuper(userId);
        if (isSuper) {
            isDisplay = null;
            creator = null;
        } else {
            isDisplay = 1;
            creator = userId;
        }
        return baseMapper.querySelect(isDisplay, creator);
    }

    /**
     * 检查是否越权
     * @param baseRoleEntity
     * @param baseUserDto
     */
    private void validatedPermission(BaseRoleEntity baseRoleEntity, BaseUserDto baseUserDto){
        // 判断是否是超级管理员
        if (Common.isSuper(baseUserDto.getId())) {
            return;
        }
        // 获取用户角色拥有的菜单ID
        List<Long> roleIds = new ArrayList<>();
        for(BaseRoleDto role : baseUserDto.getRoles()) {
            roleIds.add(role.getId());
        }
        List<Long> menuIds = baseRoleMenuService.queryMenuIdByRoleId(roleIds);
        // 判断是否越权
        if (!menuIds.containsAll(baseRoleEntity.getMenuIds())) {
            throw new RunException(5207, "该角色权限已超出你的权限!");
        }
    }

}