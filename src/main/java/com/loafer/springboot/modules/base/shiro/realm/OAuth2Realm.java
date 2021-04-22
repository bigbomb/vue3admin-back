package com.loafer.springboot.modules.base.shiro.realm;

import com.alibaba.druid.support.json.JSONUtils;
import com.loafer.springboot.common.utils.Common;
import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.modules.base.menu.service.BaseMenuService;
import com.loafer.springboot.modules.base.shiro.token.OAuth2Token;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;
import com.loafer.springboot.modules.base.token.service.BaseTokenService;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.service.BaseUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

public class OAuth2Realm extends AuthorizingRealm {
    @Resource
    BaseTokenService baseTokenService;
    @Resource
    BaseUserService baseUserService;
    @Resource
    BaseMenuService baseMenuService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 身份验证（凭证验证-token） TODO: 验证token是否有效
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 用户请求携带的token凭证
        String token = (String)authenticationToken.getPrincipal();

        BaseTokenEntity baseTokenEntity = baseTokenService.queryByToken(token);
        if (baseTokenEntity == null || baseTokenEntity.getExpiredAt().getTime() < new Date().getTime()) {
            // 凭证不正确异常
            String message = JSONUtils.toJSONString(R.error(5005));
            throw new IncorrectCredentialsException(message);
        }

        BaseUserDto baseUserDto = baseUserService.queryById(baseTokenEntity.getUserId());
        if (baseUserDto == null || baseUserDto.getStatus() == 0) {
            // 账户锁定异常
            String message = JSONUtils.toJSONString(R.error(5006));
            throw new LockedAccountException(message);
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(baseUserDto, token, getName());

        return simpleAuthenticationInfo;
    }

    /**
     * 授权验证（权限验证-permission）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户信息
        BaseUserDto baseUserDto = (BaseUserDto)principalCollection.getPrimaryPrincipal();
        Long userId = baseUserDto.getId();

        // 获取权限
        Set<String> permissions = baseMenuService.queryPermission(userId);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }


}
