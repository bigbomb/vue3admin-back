package com.loafer.springboot.modules.base.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.springboot.common.exception.RunException;
import com.loafer.springboot.common.utils.Common;
import com.loafer.springboot.common.utils.Query;
import com.loafer.springboot.common.utils.RPage;
import com.loafer.springboot.modules.base.role.dto.BaseRoleDto;
import com.loafer.springboot.modules.base.role.entity.BaseRoleMenuEntity;
import com.loafer.springboot.modules.base.user.dao.BaseUserDao;
import com.loafer.springboot.modules.base.user.dao.BaseUserRoleDao;
import com.loafer.springboot.modules.base.user.dto.BaseUserDto;
import com.loafer.springboot.modules.base.user.entity.BaseUserEntity;
import com.loafer.springboot.modules.base.user.entity.BaseUserRoleEntity;
import com.loafer.springboot.modules.base.user.service.BaseUserRoleService;
import com.loafer.springboot.modules.base.user.service.BaseUserService;
import com.loafer.springboot.modules.base.user.vo.EditUserVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("baseUserService")
public class BaseUserServiceImpl extends ServiceImpl<BaseUserDao, BaseUserEntity> implements BaseUserService {

    @Resource
    private BaseUserRoleService baseUserRoleService;
    @Resource
    private BaseUserRoleDao baseUserRoleDao;

    @Override
    public RPage<BaseUserDto> queryPage(Map<String, Object> params) {
        Page<BaseUserDto> page = new Query<BaseUserDto>().getPage(params);
        long userId = (long)params.get("userId");
        String username = (String)params.get("username");
        String nickname = (String)params.get("nickname");
        boolean isSuper = Common.isSuper(userId);
        RPage<BaseUserDto> rPage;
        if (isSuper) {
            rPage = new RPage<>(baseMapper.queryAllPage(page, username, nickname));
        } else {
            rPage = new RPage<>(baseMapper.queryByCreatorPage(page, userId, username, nickname));
        }
        return rPage;
    }

    @Override
    public BaseUserDto queryById(Long id) {
        return baseMapper.queryById(id);
    }

    @Override
    public BaseUserDto queryByUsername(String username) {
        return baseMapper.queryByUsername(username);
    }

    @Override
    @Transactional
    public void create(BaseUserEntity baseUserEntity, BaseUserDto baseUserDto) {
        validatedRole(baseUserEntity, baseUserDto);

        QueryWrapper<BaseUserEntity> wrapper = new QueryWrapper<BaseUserEntity>()
                .eq("username", baseUserEntity.getUsername());
        int count = this.count(wrapper);
        if (count != 0) {
            throw new RunException("??????????????????????????????!");
        }

        baseUserEntity.setCreatedAt(new Date());
        // ??????
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = new Sha256Hash(baseUserEntity.getPassword(), salt).toHex();
        baseUserEntity.setSalt(salt);
        baseUserEntity.setPassword(password);

        this.save(baseUserEntity);

//        List<BaseUserRoleEntity> baseUserRoleEntities = getBaseUserRoles(baseUserEntity.getId(), baseUserEntity.getRoleIds());
//        for (BaseUserRoleEntity userRoleEntity : baseUserRoleEntities) {
//            baseUserRoleDao.insert(userRoleEntity);
//        }
        baseUserRoleService.createOrUpdate(baseUserEntity.getId(), baseUserEntity.getRoleIds());
    }

    @Override
    @Transactional
    public void update(BaseUserEntity baseUserEntity, BaseUserDto baseUserDto) {
        validatedRole(baseUserEntity, baseUserDto);

        baseUserEntity.setUpdatedAt(new Date());
        // ??????????????????????????????
        if(StringUtils.isBlank(baseUserEntity.getPassword())) {
            baseUserEntity.setSalt(null);
            baseUserEntity.setPassword(null);
        } else {
            String salt = RandomStringUtils.randomAlphanumeric(20);
            String password = new Sha256Hash(baseUserEntity.getPassword(), salt).toHex();
            baseUserEntity.setSalt(salt);
            baseUserEntity.setPassword(password);
        }

        this.updateById(baseUserEntity);

        List<BaseUserRoleEntity> baseUserRoleEntities = getBaseUserRoles(baseUserEntity.getId(), baseUserEntity.getRoleIds());
        for (BaseUserRoleEntity userRoleEntity : baseUserRoleEntities) {
            baseUserRoleDao.insert(userRoleEntity);
        }
//        baseUserRoleService.createOrUpdate(baseUserEntity.getId(), baseUserEntity.getRoleIds());
    }

    @Override
    public int selfUpdate(EditUserVo editUserVo) {
        BaseUserEntity baseUserEntity = new BaseUserEntity();
        int type = 0;

        if (StringUtils.isNotBlank(editUserVo.getNewPassword())) {
            if (StringUtils.isBlank(editUserVo.getOldPassword())) {
                throw new RunException(400, "?????????????????????");
            } else {
                BaseUserEntity user = this.getById(editUserVo.getId());
                String oldPassword = new Sha256Hash(editUserVo.getOldPassword(), user.getSalt()).toHex();
                if (!oldPassword.equals(user.getPassword())) {
                    throw new RunException(4000, "??????????????????");
                }
                String newPassword = new Sha256Hash(editUserVo.getNewPassword(), user.getSalt()).toHex();
                baseUserEntity.setPassword(newPassword);
                type = 1;
            }
        }
        baseUserEntity.setId(editUserVo.getId());
        baseUserEntity.setNickname(editUserVo.getNickname());
        baseUserEntity.setMobile(editUserVo.getMobile());
        baseUserEntity.setEmail(editUserVo.getEmail());
        baseUserEntity.setAvatar(editUserVo.getAvatar());
        baseUserEntity.setUpdatedAt(new Date());
        baseUserEntity.setUpdater(editUserVo.getId());
        this.updateById(baseUserEntity);
        return type;
    }

    /**
     * ??????????????????
     * @param baseUserEntity ????????????
     * @param baseUserDto ????????????
     */
    private void validatedRole(BaseUserEntity baseUserEntity, BaseUserDto baseUserDto) {
        // ??????????????????????????????
        if (Common.isSuper(baseUserDto.getId())) {
            return;
        }
        // ???????????????????????????ID
        List<Long> roleIds = new ArrayList<>();
        for(BaseRoleDto role : baseUserDto.getRoles()) {
            roleIds.add(role.getId());
        }
        // ??????????????????
        if (!roleIds.containsAll(baseUserEntity.getRoleIds())) {
            throw new RunException(4000, "????????????????????????????????????!");
        }

    }

    /**
     * ??????????????????????????????
     * @param userId
     * @param roleIds
     * @return
     */
    private List<BaseUserRoleEntity> getBaseUserRoles(Long userId, List<Long> roleIds) {
        QueryWrapper<BaseUserRoleEntity> wrapper = new QueryWrapper<BaseUserRoleEntity>()
                .eq("user_id", userId);
        baseUserRoleDao.delete(wrapper);

        List<BaseUserRoleEntity> baseUserRoleEntities = new ArrayList<>();

        if (roleIds.size() == 0) {
            return baseUserRoleEntities;
        }

        for (Long roleId : roleIds) {
            BaseUserRoleEntity userRole = new BaseUserRoleEntity();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            baseUserRoleEntities.add(userRole);
        }

        return baseUserRoleEntities;
    }

}