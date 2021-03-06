package com.loafer.springboot.modules.base.shiro.realm;

import com.alibaba.druid.support.json.JSONUtils;
import com.loafer.springboot.common.utils.Common;
import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.modules.base.menu.service.BaseMenuService;
import com.loafer.springboot.modules.base.shiro.service.ShiroService;
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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

@Component
public class OAuth2Realm extends AuthorizingRealm {
    // TODO:  注意 敲黑板 （BeanPostProcessor） 不可以直接注入服务层到 shiro 不然会导致 事务注解失效
    // TODO:  所以这边写个 shiro 服务层 依旧注意 shiro 服务层里面也是不可以注入服务层
    /*@Resource
    BaseTokenService baseTokenService;
    @Resource
    BaseUserService baseUserService;
    @Resource
    BaseMenuService baseMenuService;*/
    @Resource
    ShiroService shiroService;

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

//        BaseTokenEntity baseTokenEntity = baseTokenService.queryByToken(token);
        BaseTokenEntity baseTokenEntity = shiroService.queryTokenByToken(token);
        if (baseTokenEntity == null || baseTokenEntity.getExpiredAt().getTime() < new Date().getTime()) {
            // 凭证不正确异常
            String message = JSONUtils.toJSONString(R.error(401, "凭证已过期，请重新登录!"));
            throw new IncorrectCredentialsException(message);
        }

//        BaseUserDto baseUserDto = baseUserService.queryById(baseTokenEntity.getUserId());
        BaseUserDto baseUserDto = shiroService.queryUserByUserId(baseTokenEntity.getUserId());
        if (baseUserDto == null || baseUserDto.getStatus() == 0) {
            // 账户锁定异常
            String message = JSONUtils.toJSONString(R.error(4000, "账户已被冻结，请联系管理员"));
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
//        Set<String> permissions = baseMenuService.queryPermission(userId);
        Set<String> permissions = shiroService.queryPermissionByUserId(userId);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }


}
