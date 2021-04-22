package com.loafer.springboot.modules.base.login.controller;

import com.loafer.springboot.common.utils.R;
import com.loafer.springboot.modules.base.AbstractController;
import com.loafer.springboot.modules.base.login.service.BaseLoginService;
import com.loafer.springboot.modules.base.login.vo.LoginVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/base")
public class BaseLoginController extends AbstractController {

    @Resource
    private BaseLoginService baseLoginService;

    /**
     * 登录
     *
     * @api {POST} /base/login
     * @apiDescription 登录
     * @apiVersion 1.0.0
     * @apiGroup BaseLogin
     * @apiName login
     * @apiParamExample 请求参数示例
     * {
     *     username: 1, // 帐号
     *     password: 1, // 密码
     *     uuid: 1, // UUID
     *     code: 1 // 验证码
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     *     data: {
     *         user_id: 1, // 用户ID
     *         token: 1, // token
     *         expired_at: 1, // 过期时间
     *         updated_at: 1 // 更新时间
     *     }
     * }
     */
    @PostMapping("/login")
    public R login(@RequestBody @Validated LoginVo loginVo) {
        return R.success(baseLoginService.login(loginVo));
    }

    /**
     * 登出
     *
     * @api {POST} /base/logout
     * @apiDescription 登录
     * @apiVersion 1.0.0
     * @apiGroup BaseLogin
     * @apiName logout
     * @apiParamExample 请求参数示例
     * {
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     message: '成功！',
     *     status: 'success'
     * }
     */
    @PostMapping("/logout")
    public R logout() {
        baseLoginService.logout(getUserId());
        return R.success();
    }

}
