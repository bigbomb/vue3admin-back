package com.loafer.springboot.modules.base.menu.controller;

import com.loafer.springboot.common.exception.RunException;
import com.loafer.springboot.common.utils.Constant;
import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.common.validator.group.Create;
import com.loafer.springboot.common.validator.group.Update;
import com.loafer.springboot.modules.base.AbstractController;
import com.loafer.springboot.modules.base.menu.dto.BaseMenuDto;
import com.loafer.springboot.modules.base.menu.entity.BaseMenuEntity;
import com.loafer.springboot.modules.base.menu.service.BaseMenuService;
import com.loafer.springboot.modules.base.vo.StatusVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/base/menu")
public class BaseMenuController extends AbstractController {

    @Resource
    private BaseMenuService baseMenuService;

    /**
     * 当前用户 菜单、权限 TODO: 超级管理员返回所有
     *
     * @api {GET} /base/menu/self/info selfInfo
     * @apiDescription 当前用户 菜单、权限
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName selfInfo
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *          permissions:["base:menu:page", "base:menu:add"] // 权限列表
     *          menus: [{
     *         	   id: '', // ID
     *             parent_id: '', // 父级ID
     *             name_cn: '', // 中文名称
     *             name_en: '', // 英文名称
     *             url: '', // 路由 、url
     *             type: '', // 类型   0：目录   1：菜单   2：按钮
     *             icon: '', // 图标
     *             sort: '', // 排序
     *             is_display: '', // 是否显示
     *             is_tab: '', // 是否显示在标签栏
     *             children: [{
     *                  id: '', // ID
     *                  parent_id: '', // 父级ID
     *                  name_cn: '', // 中文名称
     *                  name_en: '', // 英文名称
     *                  url: '', // 路由 、url
     *                  type: '', // 类型   0：目录   1：菜单   2：按钮
     *                  icon: '', // 图标
     *                  sort: '', // 排序
     *                  is_display: '', // 是否显示
     *                  is_tab: '', // 是否显示在标签栏
     *                  children：[]
     *             }]
     *         }
     *     }
     * }
     */
    @GetMapping("/self/info")
    public R selfInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("menus", baseMenuService.queryMenu(getUserId()));
        result.put("permissions", baseMenuService.queryPermission(getUserId()));

       return R.success(result);
    }

    /**
     * 菜单下拉选择 TODO: 放回当前用户拥有的菜单 权限 超级管理员所有未删除的
     *
     * @api {GET} /base/menu/self/select selfSelect
     * @apiDescription 菜单下拉选择
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName selfSelect
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '' // 英文名称
     *     }
     * }
     */
    @GetMapping("/self/select")
    @RequiresPermissions("base:menu:self:select")
    public R selfSelect() {
        return R.success(baseMenuService.queryAllMenuByRole(getUserId()));
    }

    /**
     * 菜单列表
     *
     * @api {GET} /base/menu/list list
     * @apiDescription 菜单列表
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName listByParent
     * @apiParamExample 请求参数示例
     * {
     *      parent_id: 0 // 父级ID 根目录为 0
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '', // 排序
     *         is_display: '', // 是否显示
     *         is_tab: '', // 是否显示在标签栏
     *         children: []
     *     }
     * }
     */
    @GetMapping("/list")
    @RequiresPermissions("base:menu:list")
    public R listByParent(@RequestParam("parent_id") Long parentId) {
        return R.success(baseMenuService.queryAllMenuByParentId(parentId));
    }

    /**
     * 菜单信息
     *
     * @api {GET} /base/menu/info/{id} info
     * @apiDescription 菜单信息
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName info
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '', // 排序
     *         is_display: '', // 是否显示
     *         is_tab: '', // 是否显示在标签栏
     *         children: []
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("base:menu:info")
    public R info(@PathVariable("id") Long id){
        return R.success(baseMenuService.queryById(id));
    }

    /**
     * 菜单新增
     *
     * @api {POST} /base/menu/create create
     * @apiDescription 菜单新增
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '' // 排序
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/create")
    @RequiresPermissions("base:menu:create")
    public R create(@RequestBody @Validated(Create.class) BaseMenuEntity baseMenuEntity) {
        validated(baseMenuEntity);
        baseMenuEntity.setCreator(getUserId());
        baseMenuService.create(baseMenuEntity);
        return R.success();
    }

    /**
     * 菜单编辑
     *
     * @api {POST} /base/menu/update update
     * @apiDescription 菜单新增
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '' // 排序
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/update")
    @RequiresPermissions("base:menu:update")
    public R update(@RequestBody @Validated(Update.class) BaseMenuEntity baseMenuEntity) {
        validated(baseMenuEntity);
        baseMenuEntity.setUpdater(getUserId());
        baseMenuService.update(baseMenuEntity);
        return R.success();
    }

    /**
     * 菜单删除
     *
     * @api {POST} /base/menu/delete delete
     * @apiDescription 菜单删除
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {
     *     	   id: '' // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/delete")
    @RequiresPermissions("base:menu:delete")
    public R delete(@RequestBody Map<String, Long> params) {
        Long id = params.get("id");

        List<BaseMenuDto> list = baseMenuService.queryAllMenuByParentId(id);

        if (list.size() > 0) {
            return R.error(5206, "请先删除子菜单或者按钮!");
        }

        baseMenuService.removeById(id);
        return R.success();
    }

    /**
     * 是否显示
     *
     * @api {POST} /base/menu/display display
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName display
     * @apiParamExample 请求参数示例
     * {
     *     key: '', // ID
     *     value: '' // 0：是 1：否
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @RequestMapping("/display")
    @RequiresPermissions("base:menu:display")
    public R display(@RequestBody @Validated StatusVo<Long, Integer> menuStatusVo){
        BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
        baseMenuEntity.setId(menuStatusVo.getKey());
        baseMenuEntity.setIsDisplay(menuStatusVo.getValue());

        baseMenuService.updateById(baseMenuEntity);

        return R.success();
    }

    /**
     * 是否缓存
     *
     * @api {POST} /base/menu/alive alive
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName alive
     * @apiParamExample 请求参数示例
     * {
     *     key: '', // ID
     *     value: '' // 0：是 1：否
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @RequestMapping("/alive")
    @RequiresPermissions("base:menu:alive")
    public R alive(@RequestBody @Validated StatusVo<Long, Integer> menuStatusVo){
        BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
        baseMenuEntity.setId(menuStatusVo.getKey());
        baseMenuEntity.setIsAlive(menuStatusVo.getValue());

        baseMenuService.updateById(baseMenuEntity);

        return R.success();
    }

    /**
     * 是否显示在标签栏
     *
     * @api {POST} /base/menu/tab tab
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName tab
     * @apiParamExample 请求参数示例
     * {
     *     key: '', // ID
     *     value: '' // 0：是 1：否
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @RequestMapping("/tab")
    @RequiresPermissions("base:menu:tab")
    public R tab(@RequestBody @Validated StatusVo<Long, Integer> menuStatusVo){
        BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
        baseMenuEntity.setId(menuStatusVo.getKey());
        baseMenuEntity.setIsTab(menuStatusVo.getValue());

        baseMenuService.updateById(baseMenuEntity);

        return R.success();
    }

    /**
     * 是否支持标签栏多开
     *
     * @api {POST} /base/menu/multiple multiple
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName multiple
     * @apiParamExample 请求参数示例
     * {
     *     key: '', // ID
     *     value: '' // 0：是 1：否
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @RequestMapping("/multiple")
    @RequiresPermissions("base:menu:multiple")
    public R multiple(@RequestBody @Validated StatusVo<Long, Integer> menuStatusVo){
        BaseMenuEntity baseMenuEntity = new BaseMenuEntity();
        baseMenuEntity.setId(menuStatusVo.getKey());
        baseMenuEntity.setIsMultiple(menuStatusVo.getValue());

        baseMenuService.updateById(baseMenuEntity);

        return R.success();
    }

    /**
     * 菜单下拉选择 TODO:不包括按钮
     *
     * @api {GET} /base/menu/select select
     * @apiDescription 菜单下拉选择
     * @apiVersion 1.0.0
     * @apiGroup BaseMenu
     * @apiName get
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '' // 英文名称
     *     }
     * }
     */
    @GetMapping("/select")
    @RequiresPermissions("base:menu:select")
    public R select() {
        return R.success(baseMenuService.queryNoButtonMenu());
    }

    /**
     * 校验菜单参数
     * @param baseMenuEntity 菜单实体
     */
    private void validated(BaseMenuEntity baseMenuEntity) {
        if (baseMenuEntity.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(baseMenuEntity.getUrl())) {
                throw new RunException(5007, "菜单URL不能为空");
            }
        }

        // 目录
        if (baseMenuEntity.getType() == Constant.MenuType.CATALOG.getValue()) {
            if (baseMenuEntity.getParentId() != 0) {
                throw new RunException(5007, "上级菜单只能为目录类型");
            }
        }
        // 菜单
        if (baseMenuEntity.getType() == Constant.MenuType.MENU.getValue()) {
            if (baseMenuEntity.getParentId() != 0) {
                int parentType = baseMenuService.queryById(baseMenuEntity.getParentId()).getType();
                if (parentType != Constant.MenuType.CATALOG.getValue()) {
                    throw new RunException(5007, "上级菜单只能为目录类型");
                }
            }
        }
        // 按钮
        if (baseMenuEntity.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (baseMenuEntity.getParentId() == 0) {
                throw new RunException(5007, "上级菜单只能为目录类型");
            }
            if (baseMenuEntity.getParentId() != 0) {
                int parentType = baseMenuService.queryById(baseMenuEntity.getParentId()).getType();
                if (parentType != Constant.MenuType.MENU.getValue()) {
                    throw new RunException(5007, "上级菜单只能为菜单类型");
                }
            }
        }
    }

}
