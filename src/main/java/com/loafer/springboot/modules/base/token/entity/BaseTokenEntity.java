package com.loafer.springboot.modules.base.token.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户token表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("base_token")
public class BaseTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@JsonProperty("user_id")
	@TableId(value = "user_id", type = IdType.INPUT)
	private Long userId;
	/**
	 * token
	 */
	private String token;
	/**
	 * 到期时间
	 */
	@JsonProperty("expired_at")
	private Date expiredAt;
	/**
	 * 更新时间
	 */
	@JsonProperty("updated_at")
	private Date updatedAt;

}
