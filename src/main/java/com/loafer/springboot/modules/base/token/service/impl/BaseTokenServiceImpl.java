package com.loafer.springboot.modules.base.token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.modules.base.shiro.token.TokenUtils;
import com.loafer.springboot.modules.base.token.dao.BaseTokenDao;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;
import com.loafer.springboot.modules.base.token.service.BaseTokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("baseTokenService")
public class BaseTokenServiceImpl extends ServiceImpl<BaseTokenDao, BaseTokenEntity> implements BaseTokenService {

    @Resource
    private TokenUtils tokenUtils;

    @Override
    public BaseTokenEntity createToken(Long userId) {
        // 生成token
        String token = TokenUtils.generate();
        // 获取当前时间
        Date now = new Date();
        // 计算过期时间
        Date expireTime = new Date(now.getTime() + tokenUtils.getExpire() * 1000);
        // 判断是否有token记录
        BaseTokenEntity baseTokenEntity = this.getById(userId);
        // 设置token信息
        BaseTokenEntity tokenEntity = new BaseTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        tokenEntity.setUpdatedAt(now);
        tokenEntity.setExpiredAt(expireTime);
        if (baseTokenEntity == null) { // 新增token记录
            this.save(tokenEntity);
        } else { // 更新token记录
            this.updateById(tokenEntity);
        }
        return tokenEntity;
    }

    @Override
    public void destroyToken(Long userId) {
        this.removeById(userId);
    }

    @Override
    public BaseTokenEntity queryByToken(String token) {
        return baseMapper.queryByToken(token);
    }

    @Override
    public BaseTokenEntity queryByUserId(Long userId) {
        return baseMapper.queryByUserId(userId);
    }
}