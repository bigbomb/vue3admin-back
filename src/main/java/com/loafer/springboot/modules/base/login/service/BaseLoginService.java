package com.loafer.springboot.modules.base.login.service;

import com.loafer.springboot.modules.base.login.vo.LoginVo;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;

import java.util.Map;

/**
 * 登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface BaseLoginService {

    BaseTokenEntity login(LoginVo loginVo);

    void logout(Long userId);

}
