package com.loafer.springboot.modules.base.token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;

/**
 * 用户表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
public interface BaseTokenService extends IService<BaseTokenEntity> {

    BaseTokenEntity createToken (Long userId);

    void destroyToken (Long userId);

    BaseTokenEntity queryByToken (String token);

    BaseTokenEntity queryByUserId (Long userId);



}

