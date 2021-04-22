package com.loafer.springboot.modules.base.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.loafer.springboot.common.validator.group.Create;
import com.loafer.springboot.common.validator.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "base_user")
public class BaseUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为null", groups = Update.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {Create.class, Update.class})
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Create.class})
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 昵称
     */
    @NotBlank(message = "用户名不能为空", groups = {Create.class, Update.class})
    private String nickname;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确", groups = {Create.class, Update.class})
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
     * 创建者
     */
    private Long creator;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
    /**
     * 更新者
     */
    private Long updater;
    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private Date updatedAt;
    /**
     *
     */
    @TableLogic
    private Integer deleted;
    /**
     * 角色ID
     */
    @JsonProperty("role_ids")
    @TableField(exist = false)
    private List<Long> roleIds;

}
