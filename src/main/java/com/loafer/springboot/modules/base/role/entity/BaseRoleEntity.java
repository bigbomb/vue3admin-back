package com.loafer.springboot.modules.base.role.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.loafer.springboot.common.validator.group.Create;
import com.loafer.springboot.common.validator.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("base_role")
public class BaseRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@NotNull(message = "id不能为空", groups = Update.class)
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 名称
	 */
	@NotBlank(message = "角色名称不能为空", groups = {Create.class, Update.class})
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否显示
	 */
	@JsonProperty("is_display")
	private Integer isDisplay;
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
	 * 菜单ID
	 */
	@JsonProperty("menu_ids")
	@TableField(exist = false)
	private List<Long> menuIds;

}
