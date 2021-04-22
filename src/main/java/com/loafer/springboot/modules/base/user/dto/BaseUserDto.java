package com.loafer.springboot.modules.base.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loafer.springboot.modules.base.role.dto.BaseRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户DTO
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private Long avatar;
    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
    /**
     * 用户角色
     */
    private List<BaseRoleDto> roles;

}
