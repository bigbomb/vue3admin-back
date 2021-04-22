package com.loafer.springboot.modules.base.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.modules.base.menu.dao.BaseMenuDao;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuDto;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuSelectDto;
import com.loafer.springboot.modules.base.menu.entity.BaseMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface BaseMenuService extends IService<BaseMenuEntity> {

    /**
     * 获取权限 TODO:超级管理员获取所有权限
     * @param userId 用户ID
     * @return
     */
    Set<String> queryPermission(Long userId);

    /**
     * 获取菜单 TODO:超级管理员获取所有菜单
     * @param userId 用户ID
     * @return
     */
    List<BaseMenuDto> queryMenu(Long userId);

    /**
     * 获取未删除的菜单
     * @param parentId 父级ID
     * @return
     */
    List<BaseMenuDto> queryAllMenuByParentId(Long parentId);

    /**
     * 获取所有未删除菜单 TODO:不包括按钮 非树形
     * @return
     */
    List<BaseMenuSelectDto> queryNoButtonMenu();

    /**
     * 根据角色获取 菜单 权限
     * @return
     */
    List<BaseMenuSelectDto> queryAllMenuByRole(Long userId);

    /**
     * 信息
     * @param id
     * @return
     */
    BaseMenuDto queryById(Long id);

    /**
     * 新增
     * @param baseMenuEntity 菜单实体
     */
    void create(BaseMenuEntity baseMenuEntity);

    /**
     * 编辑
     * @param baseMenuEntity 菜单实体
     */
    void update(BaseMenuEntity baseMenuEntity);
}

