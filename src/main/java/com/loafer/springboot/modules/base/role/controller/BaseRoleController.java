package com.loafer.springboot.modules.base.role.controller;

import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.common.validator.group.Create;
import com.loafer.springboot.common.validator.group.Update;
import com.loafer.springboot.modules.base.AbstractController;
import com.loafer.springboot.modules.base.role.entity.BaseRoleEntity;
import com.loafer.springboot.modules.base.role.service.BaseRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
@RestController
@RequestMapping("/base/role")
public class BaseRoleController extends AbstractController {
    @Resource
    private BaseRoleService baseRoleService;

    /**
     * 角色分页列表 TODO:只显示当前用户创建角色 超级管理员显示所有角色
     *
     * @api {GET} /base/role/page page
     * @apiDescription 角色列表
     * @apiVersion 1.0.0
     * @apiGroup BaseRole
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
     *             name: '', // 角色名
     *             remark: '', // 备注
     *             created_at: '' // 创建时间
     *         }]
     *     }
     * }
     */
    @GetMapping("/page")
    @RequiresPermissions("base:role:page")
    public R page(@RequestParam Map<String, Object> params){
        params.put("userId", getUserId());
        return R.success(baseRoleService.queryPage(params));
    }

    /**
     * 角色信息
     *
     * @api {GET} /base/role/info/{id} info
     * @apiDescription 角色信息
     * @apiVersion 1.0.0
     * @apiGroup BaseRole
     * @apiName info
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *          id: '', // ID
     *          name: '', // 角色名
     *          remark: '', // 备注
     *          created_at: '' // 创建时间
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("base:role:info")
    public R info(@PathVariable("id") Long id) {
        return R.success(baseRoleService.queryById(id, getUserId()));
    }

    /**
     * 角色新增
     *
     * @api {POST} /base/role/create create
     * @apiDescription 新增
     * @apiVersion 1.0.0
     * @apiGroup BaseRole
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      name: '', // 角色名称
     *      remark: '', // 备注
     *      menu_ids: [] // 菜单权限ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/create")
    @RequiresPermissions("base:role:create")
    public R create(@RequestBody @Validated(Create.class) BaseRoleEntity baseRoleEntity) {
        baseRoleService.create(baseRoleEntity, getUser());
        return R.success();
    }

    /**
     * 角色编辑
     *
     * @api {POST} /base/role/update update
     * @apiDescription 编辑
     * @apiVersion 1.0.0
     * @apiGroup BaseRole
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      name: '', // 角色名称
     *      remark: '', // 备注
     *      menu_ids: [] // 菜单权限ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/update")
    @RequiresPermissions("base:role:update")
    public R update(@RequestBody @Validated(Update.class) BaseRoleEntity baseRoleEntity) {
        baseRoleService.update(baseRoleEntity, getUser());
        return R.success();
    }

    /**
     * 角色批量删除
     *
     * @api {POST} /base/role/delete delete
     * @apiDescription 批量删除
     * @apiVersion 1.0.0
     * @apiGroup BaseRole
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
    @RequiresPermissions("base:role:delete")
    public R delete(@RequestBody Long[] ids) {
        baseRoleService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    /**
     * 角色下拉 TODO: 超级管理查询未删除的 当前用户查询自己创建的
     *
     * @api {POST} /base/role/select select
     * @apiDescription 角色下拉
     * @apiVersion 1.0.0
     * @apiGroup BaseRole
     * @apiName select
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'，
     *     data: [{
     *          id: '', // ID
     *          name: '', // 角色名称
     *          remark: '', // 备注
     *     }]
     * }
     */
    @GetMapping("/select")
    @RequiresPermissions("base:role:select")
    public R select() {
        return R.success(baseRoleService.querySelect(getUserId()));
    }
}
