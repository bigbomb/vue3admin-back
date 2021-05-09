package com.loafer.springboot.modules.base.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuDto;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuSelectDto;
import com.loafer.springboot.modules.base.menu.entity.BaseMenuEntity;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;

import java.util.List;
import java.util.Set;

/**
 * 菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface ShiroService {

    /**
     * 获取token
     * @param token
     * @return
     */
    BaseTokenEntity queryTokenByToken(String token);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    BaseUserDto queryUserByUserId(Long userId);

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    Set<String> queryPermissionByUserId(Long userId);
}

