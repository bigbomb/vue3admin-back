package com.loafer.springboot.modules.base.quartz.entity;

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
 * 定时任务日志表
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("base_schedule_task_log")
public class BaseScheduleTaskLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 任务id
     */
    @JsonProperty("task_id")
    private Long taskId ;
    /**
     * 参数
     */
    @JsonProperty("bean_name")
    private String beanName;
    /**
     * 参数
     */
    private String params;
    /**
     * 任务状态 0：失败 1：成功
     */
    private Integer status;
    /**
     * 成功信息
     */
    private String message;
    /**
     * 失败信息
     */
    private String error;
    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;

}
