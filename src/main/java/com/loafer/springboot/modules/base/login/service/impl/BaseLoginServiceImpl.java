package com.loafer.springboot.modules.base.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loafer.springboot.common.exception.RunException;
import com.loafer.springboot.modules.base.captcha.service.BaseCaptchaService;
import com.loafer.springboot.modules.base.login.service.BaseLoginService;
import com.loafer.springboot.modules.base.login.vo.LoginVo;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;
import com.loafer.springboot.modules.base.token.service.BaseTokenService;
import com.loafer.springboot.modules.base.user.entity.BaseUserEntity;
import com.loafer.springboot.modules.base.user.service.BaseUserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("baseLoginService")
public class BaseLoginServiceImpl implements BaseLoginService {

    @Resource
    private BaseCaptchaService baseCaptchaService;
    @Resource
    private BaseUserService baseUserService;
    @Resource
    private BaseTokenService baseTokenService;

    @Override
    public BaseTokenEntity login(LoginVo loginVo) {
        boolean result = baseCaptchaService.validateCode(loginVo.getUuid(), loginVo.getCode());
        if (!result) {
            throw new RunException(5202);
        }

        QueryWrapper<BaseUserEntity> wrapper =
                new QueryWrapper<BaseUserEntity>()
                        .eq("username", loginVo.getUsername());
        BaseUserEntity user = baseUserService.getOne(wrapper);
        if (user == null || !user.getPassword().equals(new Sha256Hash(loginVo.getPassword(), user.getSalt()).toHex())) {
            throw new RunException(5203);
        }

        return baseTokenService.createToken(user.getId());
    }

    @Override
    public void logout(Long userId) {
        baseTokenService.destroyToken(userId);
    }
}
