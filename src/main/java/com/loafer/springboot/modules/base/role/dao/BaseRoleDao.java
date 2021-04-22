package com.loafer.springboot.modules.base.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loafer.springboot.modules.base.role.dto.BaseRoleDto;
import com.loafer.springboot.modules.base.role.dto.BaseRoleSelectDto;
import com.loafer.springboot.modules.base.role.entity.BaseRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Mapper
public interface BaseRoleDao extends BaseMapper<BaseRoleEntity> {

    /**
     * 分页
     * @param page 分页对象
     * @param userId 创建者
     * @param isDisplay 是否显示
     * @return
     */
    IPage<BaseRoleDto> queryPage(Page<BaseRoleDto> page,
                                 @Param("userId") Long userId,
                                 @Param("name") String name,
                                 @Param("isDisplay") Integer isDisplay);

    /**
     * ID查询信息
     * @param id key
     * @return
     */
    BaseRoleDto queryById(@Param("id") Long id);

    /**
     * 查询下拉列表
     * @param isDisplay 是否显示
     * @param userId 创建者
     * @return
     */
    List<BaseRoleSelectDto> querySelect(@Param("isDisplay") Integer isDisplay, @Param("userId") Long userId);

}
