package com.loafer.springboot.modules.base.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.role.dto.BaseRoleDto;
import com.loafer.springboot.modules.base.role.dto.BaseRoleSelectDto;
import com.loafer.springboot.modules.base.role.entity.BaseRoleEntity;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface BaseRoleService extends IService<BaseRoleEntity> {

    /**
     * 分页 TODO: 超级管理员显示所有 非 显示当前用户创建的
     * @param params 分页参数
     * @return
     */
    RPage<BaseRoleDto> queryPage(Map<String, Object> params);

    /**
     * ID查询信息
     * @param id key
     * @return
     */
    BaseRoleDto queryById(Long id, Long userId);

    /**
     * 新增
     * @param baseRoleEntity 角色实体
     * @param baseUserDto 用户实体
     */
    void create(BaseRoleEntity baseRoleEntity, BaseUserDto baseUserDto);

    /**
     * 编辑
     * @param baseRoleEntity 角色实体
     * @param baseUserDto 用户实体
     */
    void update(BaseRoleEntity baseRoleEntity, BaseUserDto baseUserDto);

    /**
     * 查询角色下拉 TODO: 超级管理查询未删除的 当前用户查询自己创建的
     * @param userId
     * @return
     */
    List<BaseRoleSelectDto> querySelect(Long userId);

}

