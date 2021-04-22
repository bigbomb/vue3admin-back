package com.loafer.springboot.modules.base.token.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loafer.springboot.modules.base.token.entity.BaseTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BaseTokenDao extends BaseMapper<BaseTokenEntity> {

    BaseTokenEntity queryByToken (@Param("token") String token);

    BaseTokenEntity queryByUserId (@Param("userId") Long userId);

}
