package com.loafer.springboot.modules.base.user.vo;

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
 * 修改用户信息Vo
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Data
public class EditUserVo {

    private Long id;
    /**
     * 昵称
     */
    @NotBlank(message = "用户名不能为空")
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
     * 原密码
     */
    @JsonProperty("old_password")
    private String oldPassword;
    /**
     * 新密码
     */
    @JsonProperty("new_password")
    private String newPassword;

}
