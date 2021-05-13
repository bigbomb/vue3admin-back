package com.loafer.springboot.modules.base.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

/**
 * 定时任务表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("base_schedule_task")
public class BaseScheduleTaskEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String TASK_PARAM_KEY = "TASK_PARAM_KEY";

    @NotNull(message = "ID不能为null", groups = Update.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * spring bean名称
     */
    @NotBlank(message = "spring bean名称不能为空", groups = {Create.class, Update.class})
    @JsonProperty("bean_name")
    private String beanName;
    /**
     * 参数
     */
    private String params;
    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空", groups = {Create.class, Update.class})
    @JsonProperty("cron_expression")
    private String cronExpression;
    /**
     * 任务状态  0：暂停  1：正常
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;

}
