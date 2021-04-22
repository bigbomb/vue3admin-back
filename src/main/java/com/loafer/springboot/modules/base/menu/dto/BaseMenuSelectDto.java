package com.loafer.springboot.modules.base.menu.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BaseMenuSelectDto {
    @TableId
    private Long id;
    /**
     * 父ID
     */
    @JsonProperty("parent_id")
    private Long parentId;
    /**
     * 中文名称
     */
    @JsonProperty("name_cn")
    private String nameCn;
    /**
     * 英文名称
     */
    @JsonProperty("name_en")
    private String nameEn;
}
