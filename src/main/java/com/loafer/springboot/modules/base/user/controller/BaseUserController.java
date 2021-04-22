package com.loafer.springboot.modules.base.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.common.validator.group.Create;
import com.loafer.springboot.common.validator.group.Update;
import com.loafer.springboot.modules.base.AbstractController;
import com.loafer.springboot.modules.base.user.entity.BaseUserEntity;
import com.loafer.springboot.modules.base.user.service.BaseUserService;
import com.loafer.springboot.modules.base.user.vo.EditUserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统用户
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@RestController
@RequestMapping("/base/user")
public class BaseUserController extends AbstractController {
    @Resource
    private BaseUserService baseUserService;

    /**
     * 当前登录用户信息
     *
     * @api {GET} /base/user/self/info selfInfo
     * @apiDescription 当前登录用户信息
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
     * @apiName selfInfo
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *         	   id: '', // ID
     *             username: '', // 用户名
     *             nickname: '', // 昵称
     *             mobile: '', // 手机
     *             email: '', // 邮箱
     *             avatar: '', // 头像
     *             status: '', // 状态
     *             roles: [{
     *                 id: '', // 角色ID
     *                 name: '', // 角色名称
     *                 remark: '' // 角色备注
     *             }]
     *         }
     *     }
     * }
     */
    @GetMapping("/self/info")
    public R selfInfo(){
        return R.success(getUser());
    }

    /**
     * 编辑当前用户信息
     *
     * @api {POST} /base/user/self/update selfUpdate
     * @apiDescription 编辑
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
     * @apiName selfUpdate
     * @apiParamExample 请求参数示例
     * {
     *          nickname: '', // 昵称
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          avatar: '', // 头像
     *          old_password: '', // 原密码
     *          new_password: '', // 新密码
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success',
     *     data: 0 // 0: 未修改密码 1: 修改了密码
     * }
     */
    @PostMapping("/self/update")
    public R selfUpdate(@RequestBody @Validated EditUserVo editUserVo) {
        editUserVo.setId(getUserId());
        return R.success(baseUserService.selfUpdate(editUserVo));
    }

    /**
     * 系统用户分页列表 TODO:只显示当前用户创建用户 超级管理员显示所有用户
     *
     * @api {GET} /base/user/page page
     * @apiDescription 系统用户列表
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     current: 1, // 当前页
     *     size: 10 // 页面大小
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
     *             username: '', // 用户名
     *             nickname: '', // 昵称
     *             mobile: '', // 手机
     *             email: '', // 邮箱
     *             avatar: '', // 头像
     *             status: '', // 状态
     *             roles: [{
     *                 id: '', // 角色ID
     *                 name: '', // 角色名称
     *                 remark: '' // 角色备注
     *             }]
     *         }]
     *     }
     * }
     */
    @GetMapping("/page")
    @RequiresPermissions("base:user:page")
    public R page(@RequestParam Map<String, Object> params){
        params.put("userId", getUserId());
        return R.success(baseUserService.queryPage(params));
    }

    /**
     * 系统用户信息
     *
     * @api {GET} /base/user/info/{id} info
     * @apiDescription 系统用户信息
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
     * @apiName info
     * @apiParamExample 请求参数示例
     * {
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *          id: '', // ID
     *          username: '', // 用户名
     *          nickname: '', // 昵称
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          avatar: '', // 头像
     *          status: '', // 状态
     *          roles: [{
     *              id: '', // 角色ID
     *              name: '', // 角色名称
     *              remark: '' // 角色备注
     *          }]
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("base:user:info")
    public R info(@PathVariable("id") Long id) {
        return R.success(baseUserService.queryById(id));
    }

    /**
     * 新增
     *
     * @api {POST} /base/user/create create
     * @apiDescription 新增
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *          username: '', // 用户名
     *          password: '', // 密码
     *          nickname: '', // 昵称
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          avatar: '' // 头像
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/create")
    @RequiresPermissions("base:user:create")
    public R create(@RequestBody @Validated(Create.class) BaseUserEntity baseUserEntity) {
        baseUserService.create(baseUserEntity, getUser());
        return R.success();
    }

    /**
     * 编辑
     *
     * @api {POST} /base/user/update update
     * @apiDescription 编辑
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *          id: '', // ID
     *          username: '', // 用户名
     *          password: '', // 密码
     *          nickname: '', // 昵称
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          avatar: '' // 头像
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/update")
    @RequiresPermissions("base:user:update")
    public R update(@RequestBody @Validated(Update.class) BaseUserEntity baseUserEntity) {
        baseUserService.update(baseUserEntity, getUser());
        return R.success();
    }

    /**
     * 用户批量删除
     *
     * @api {POST} /base/user/delete delete
     * @apiDescription 批量删除
     * @apiVersion 1.0.0
     * @apiGroup BaseUser
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {
     *      ids: [], // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/delete")
    @RequiresPermissions("base:user:delete")
    public R delete(@RequestBody Long[] ids) {
        baseUserService.removeByIds(Arrays.asList(ids));
        return R.success();
    }
}
