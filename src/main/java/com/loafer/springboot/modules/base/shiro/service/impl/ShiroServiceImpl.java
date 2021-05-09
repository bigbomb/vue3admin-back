package com.loafer.springboot.modules.base.shiro.service.impl;

import com.loafer.springboot.common.utils.Common;
import com.loafer.springboot.modules.base.menu.dao.BaseMenuDao;
import com.loafer.springboot.modules.base.menu.service.BaseMenuService;
import com.loafer.springboot.modules.base.shiro.service.ShiroService;
import com.loafer.springboot.modules.base.token.dao.BaseTokenDao;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;
import com.loafer.springboot.modules.base.token.service.BaseTokenService;
import com.loafer.springboot.modules.base.user.dao.BaseUserDao;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.service.BaseUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;

/**
 * shiro
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

//    @Resource
//    BaseTokenService baseTokenService;
//    @Resource
//    BaseUserService baseUserService;
//    @Resource
    BaseMenuService baseMenuService;
    @Resource
    BaseTokenDao baseTokenDao;
    @Resource
    BaseUserDao baseUserDao;
    @Resource
    BaseMenuDao baseMenuDao;


    @Override
    public BaseTokenEntity queryTokenByToken(String token) {
//        return baseTokenService.queryByToken(token);
        return baseTokenDao.queryByToken(token);
    }

    @Override
    public BaseUserDto queryUserByUserId(Long userId) {
//        return baseUserService.queryById(userId);
        return baseUserDao.queryById(userId);
    }

    @Override
    public Set<String> queryPermissionByUserId(Long userId) {
//        return baseMenuService.queryPermission(userId);
        List<String> permissions;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(userId);
        if (isSuper) { // 是超级管理员
            permissions = baseMenuDao.queryAllPermission();
        } else { // 非超级管理员
            permissions = baseMenuDao.queryPermissionByUserId(userId);
        }
        return getPermissionSet(permissions);
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
}