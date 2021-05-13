package com.loafer.springboot.modules.base.quartz.controller;

import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.common.validator.group.Create;
import com.loafer.springboot.common.validator.group.Update;
import com.loafer.springboot.modules.base.quartz.entity.BaseScheduleTaskEntity;
import com.loafer.springboot.modules.base.quartz.service.BaseScheduleTaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 定时任务
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/base/schedule/task")
public class BaseScheduleTaskController {
    @Resource
    private BaseScheduleTaskService baseScheduleTaskService;

    /**
     * 分页列表
     *
     * @api {GET} /base/schedule/task/page page
     * @apiDescription 定时任务分页列表
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     current: 1, // 当前页
     *     size: 10, // 页面大小
     *     bean_name: "" // spring bean名称
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *         current: 1, // 当前页
     *         size: 1, // 页面大小
     *         total: 1, // 总条数
     *         pages: 1, // 总页数
     *         list: [{
     *         	   id: '', // ID
     *             bean_name: '', // spring bean名称
     *             params: '', // 参数
     *             cron_expression: '', // cron表达式
     *             status: '', // 任务状态  0：暂停  1：正常
     *             remark: '', // 备注
     *             created_at: ‘’ // 创建时间
     *         }]
     *     }
     * }
     */
    @GetMapping("/page")
    @RequiresPermissions("base:schedule:task:page")
    public R get(@RequestParam Map<String, Object> params){
        return R.success(baseScheduleTaskService.queryPage(params));
    }

    /**
     * 信息
     *
     * @api {GET} /base/schedule/task/info/{id} info
     * @apiDescription 定时任务信息
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName info
     * @apiParamExample 请求参数示例
     * {
     *     id: 1 // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *         id: '', // ID
     *         bean_name: '', // spring bean名称
     *         params: '', // 参数
     *         cron_expression: '', // cron表达式
     *         status: '', // 任务状态  0：暂停  1：正常
     *         remark: '', // 备注
     *         created_at: ‘’ // 创建时间
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("base:schedule:task:info")
    public R get(@PathVariable("id") Long id){
        return R.success(baseScheduleTaskService.getById(id));
    }

    /**
     * 新增
     *
     * @api {POST} /base/schedule/task/create create
     * @apiDescription 定时任务新增
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *     bean_name: '', // spring bean名称
     *     params: '', // 参数
     *     cron_expression: '', // cron表达式
     *     remark: '' // 备注
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/create")
    @RequiresPermissions("base:schedule:task:create")
    public R create(@RequestBody @Validated(Create.class) BaseScheduleTaskEntity baseScheduleTaskEntity) {
        baseScheduleTaskService.create(baseScheduleTaskEntity);
        return R.success();
    }

    /**
     * 编辑
     *
     * @api {POST} /base/schedule/task/update update
     * @apiDescription 定时任务编辑
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *     id: '', // ID
     *     bean_name: '', // spring bean名称
     *     params: '', // 参数
     *     cron_expression: '', // cron表达式
     *     remark: '' // 备注
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/update")
    @RequiresPermissions("base:schedule:task:update")
    public R update(@RequestBody @Validated(Update.class) BaseScheduleTaskEntity baseScheduleTaskEntity) {
        baseScheduleTaskService.update(baseScheduleTaskEntity);
        return R.success();
    }

    /**
     * 批量删除
     *
     * @api {POST} /base/schedule/task/delete delete
     * @apiDescription 定时任务批量删除
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {
     *     ids: '' // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/delete")
    @RequiresPermissions("base:schedule:task:delete")
    public R delete(@RequestBody Long[] ids) {
        baseScheduleTaskService.deleteBatch(ids);
        return R.success();
    }

    /**
     * 批量立即执行
     *
     * @api {POST} /base/schedule/task/run run
     * @apiDescription 定时任务批量立即执行
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName run
     * @apiParamExample 请求参数示例
     * {
     *     ids: '' // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/run")
    @RequiresPermissions("base:schedule:task:run")
    public R run(@RequestBody Long[] ids) {
        baseScheduleTaskService.runBatch(ids);
        return R.success();
    }

    /**
     * 批量恢复
     *
     * @api {POST} /base/schedule/task/resume resume
     * @apiDescription 定时任务批量恢复
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName resume
     * @apiParamExample 请求参数示例
     * {
     *     ids: '' // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/resume")
    @RequiresPermissions("base:schedule:task:resume")
    public R resume(@RequestBody Long[] ids) {
        baseScheduleTaskService.resumeBatch(ids);
        return R.success();
    }

    /**
     * 批量暂停
     *
     * @api {POST} /base/schedule/task/pause pause
     * @apiDescription 定时任务批量暂停
     * @apiVersion 1.0.0
     * @apiGroup BaseScheduleTask
     * @apiName pause
     * @apiParamExample 请求参数示例
     * {
     *     ids: '' // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/pause")
    @RequiresPermissions("base:schedule:task:pause")
    public R pause(@RequestBody Long[] ids) {
        baseScheduleTaskService.pauseBatch(ids);
        return R.success();
    }

}
