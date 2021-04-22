package com.loafer.springboot.modules.base.quartz.controller;

import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.modules.base.quartz.service.BaseScheduleTaskLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 定时任务日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/base/schedule/log")
public class BaseScheduleTaskLogController {
    @Resource
    private BaseScheduleTaskLogService baseScheduleTaskLogService;

    /**
     * 系统用户分页列表
     *
     * @api {GET} /base/schedule/log/page page
     * @apiDescription 系统用户列表
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
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
     *             task_id: '', // 任务id
     *             bean_name: '', // spring bean名称
     *             params: '', // 参数
     *             status: '', // 任务状态 0：失败 1：成功
     *             message: '', // 成功信息
     *             error: '', // 失败信息
     *             times: '', // 耗时(单位：毫秒)
     *             created_at: ‘’ // 创建时间
     *         }]
     *     }
     * }
     */
    @GetMapping("/page")
    @RequiresPermissions("base:schedule:log:page")
    public R get(@RequestParam Map<String, Object> params){
        return R.success(baseScheduleTaskLogService.queryPage(params));
    }

    /**
     * 系统用户分页列表 TODO:只显示当前用户创建用户 超级管理员显示所有用户
     *
     * @api {GET} /base/schedule/task/page page
     * @apiDescription 系统用户列表
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
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
     *             task_id: '', // 任务id
     *             bean_name: '', // spring bean名称
     *             params: '', // 参数
     *             status: '', // 任务状态 0：失败 1：成功
     *             message: '', // 成功信息
     *             error: '', // 失败信息
     *             times: '', // 耗时(单位：毫秒)
     *             created_at: ‘’ // 创建时间
     *         }]
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("base:schedule:log:info")
    public R get(@PathVariable("id") Long id){
        return R.success(baseScheduleTaskLogService.getById(id));
    }

}
