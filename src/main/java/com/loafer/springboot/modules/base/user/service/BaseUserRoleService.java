package com.loafer.springboot.modules.base.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.entity.BaseUserEntity;
import com.loafer.springboot.modules.base.user.entity.BaseUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
public interface BaseUserRoleService extends IService<BaseUserRoleEntity> {

    /**
     * 用户角色关联新增、编辑
     * @param userId
     * @param roleIds
     */
    public void createOrUpdate(Long userId, List<Long> roleIds);


}

