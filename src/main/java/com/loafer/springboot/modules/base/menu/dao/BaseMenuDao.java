package com.loafer.springboot.modules.base.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuDto;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuSelectDto;
import com.loafer.springboot.modules.base.menu.entity.BaseMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface BaseMenuDao extends BaseMapper<BaseMenuEntity> {

    /**
     * 获取所有的权限
     * @return
     */
    List<String> queryAllPermission();

    /**
     * 获取用户拥有的权限
     * @param userId 用户ID
     * @return
     */
    List<String> queryPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据父级ID获取菜单
     * @param parentId
     * @return
     */
    List<BaseMenuDto> queryMenuByParentId(@Param("parentId") Long parentId, @Param("menuIds") List<Long> menuIds);

    /**
     * 获取所有菜单ID
     * @return
     */
    List<Long> queryAllMenuId();

    /**
     * 获取未删除的菜单
     * @param parentId 父级ID
     * @return
     */
    List<BaseMenuDto> queryAllMenuByParentId(@Param("parentId") Long parentId);

    /**
     * 获取所有有效菜单 非树形
     * @param types 需要查询的类型
     * @param menuIds 菜单ID
     * @param isDisplay 是否显示
     * @return
     */
    List<BaseMenuSelectDto> queryAllMenu(@Param("types") List<Integer> types,
                                         @Param("menuIds") List<Long> menuIds,
                                         @Param("isDisplay") Integer isDisplay);

    /**
     * 信息
     * @param id
     * @return
     */
    BaseMenuDto queryById(@Param("id") Long id);

}
