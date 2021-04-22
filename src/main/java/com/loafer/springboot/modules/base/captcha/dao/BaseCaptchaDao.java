package com.loafer.springboot.modules.base.captcha.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loafer.springboot.modules.base.captcha.entity.BaseCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface BaseCaptchaDao extends BaseMapper<BaseCaptchaEntity> {

}
