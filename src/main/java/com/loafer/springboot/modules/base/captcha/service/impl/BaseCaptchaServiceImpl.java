package com.loafer.springboot.modules.base.captcha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.loafer.springboot.common.exception.RunException;
import com.loafer.springboot.common.utils.Constant;
import com.loafer.springboot.common.utils.DateUtils;
import com.loafer.springboot.modules.base.captcha.dao.BaseCaptchaDao;
import com.loafer.springboot.modules.base.captcha.entity.BaseCaptchaEntity;
import com.loafer.springboot.modules.base.captcha.service.BaseCaptchaService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 验证码
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("baseCaptchaService")
public class BaseCaptchaServiceImpl extends ServiceImpl<BaseCaptchaDao, BaseCaptchaEntity> implements BaseCaptchaService {

    @Resource
    private Producer producer;

    @Override
    public BufferedImage createCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new RunException(400, Constant.VERIFICATION_ERROR);
        }
        String code = producer.createText();
        BaseCaptchaEntity baseCaptchaEntity = new BaseCaptchaEntity();
        baseCaptchaEntity.setUuid(uuid);
        baseCaptchaEntity.setCode(code);
        baseCaptchaEntity.setExpiredAt(DateUtils.addMinutes(new Date(), 1));
        this.save(baseCaptchaEntity);

        return producer.createImage(code);
    }

    @Override
    public boolean validateCode(String uuid, String code) {
        QueryWrapper<BaseCaptchaEntity> wrapper =
                new QueryWrapper<BaseCaptchaEntity>()
                        .eq("uuid", uuid);
        BaseCaptchaEntity baseCaptchaEntity = this.getOne(wrapper);
        if(baseCaptchaEntity == null) {
            return false;
        }

        // 验证存在验证码之后删除验证码
        this.removeById(baseCaptchaEntity.getUuid());

        if (baseCaptchaEntity.getCode().equals(code) && baseCaptchaEntity.getExpiredAt().getTime() >= new Date().getTime()) {
            return true;
        }

        return false;
    }
}