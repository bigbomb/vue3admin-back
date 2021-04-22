package com.loafer.springboot.modules.base.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.entity.BaseUserEntity;
import com.loafer.springboot.modules.base.user.vo.EditUserVo;

import java.util.Map;

/**
 * 用户表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
public interface BaseUserService extends IService<BaseUserEntity> {

    /**
     * 查询系统用户
     * @param params 分页参数
     * @return
     */
    RPage<BaseUserDto> queryPage(Map<String, Object> params);

    /**
     * 用户ID查询用户信息
     * @param id 用户ID
     * @return
     */
    BaseUserDto queryById (Long id);

    /**
     * 用户名查询用户信息
     * @param username 用户名
     * @return
     */
    BaseUserDto queryByUsername (String username);

    /**
     * 新增
     * @param baseUserEntity 用户实体
     */
    void create(BaseUserEntity baseUserEntity, BaseUserDto baseUserDto);

    /**
     * 编辑
     * @param baseUserEntity 用户实体
     */
    void update(BaseUserEntity baseUserEntity, BaseUserDto baseUserDto);

    /**
     * 编辑信息
     * @param editUserVo 修改信息Vo
     * @return int
     */
    int selfUpdate(EditUserVo editUserVo);

}

