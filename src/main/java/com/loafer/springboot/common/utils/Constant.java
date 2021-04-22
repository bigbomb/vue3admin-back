package com.loafer.springboot.common.utils;

/**
 * 常量工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final Long[] SUPER_ADMINISTRATOR = { 1L };

    /**
     * token 键值
     */
    public static final String TOKEN_KEY = "token";

    /**
     * 分页
     */
    public enum Page {
        CURRENT("current"),
        SIZE("size");

        private String value;

        Page(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 状态码
     */
    public enum StatusCode {

        NO_INVALID_TOKEN_ERROR(5001, "无效的授权令牌!"),
        NO_PERMISSION_ERROR(5002, "没有权限，请联系管理员授权!"),
        DATABASE_EXIST_ERROR(5003, "数据库中已存在该记录!"),
        TOKEN_GENERATOR_ERROR(5004, "生成Token失败!"),
        TOKEN_EXPIRED_ERROR(5005, "凭证已过期，请重新登录!"),
        ACCOUNT_LOCKED_ERROR(5006, "账户已被冻结，请联系管理员!"),
        ENTITY_VERIFICATION_ERROR(5007, "实体校验异常："),
        REQUEST_METHOD_ERROR(5008, "不支持请求方法："),

        QUARTZ_CREATE_ERROR(5100, "创建定时任务失败!"),
        QUARTZ_UPDATE_ERROR(5101, "更新定时任务失败!"),
        QUARTZ_RUN_ERROR(5102, "立即执行定时任务失败!"),
        QUARTZ_PAUSE_ERROR(5103, "暂停定时任务失败!"),
        QUARTZ_RESUME_ERROR(5104, "恢复定时任务失败!"),
        QUARTZ_DELETE_ERROR(5105, "删除定时任务失败!"),
        QUARTZ_CHECK_ERROR(5106, "验证定时任务是否存在失败!"),
        QUARTZ_GET_CRON_TRIGGER_ERROR(5107, "获取定时任务CronTrigger出现异常!"),

        REDIS_KEY_EMPTY_ERROR(5200, "redis key 不可以为空!"),
        UUID_EMPTY_ERROR(5201, "UUID不可以为空!"),
        CAPTCHA_WRONG_ERROR(5202, "验证码不正确!"),
        LOGIN_ERROR(5203, "帐号或密码不正确!"),
        DELETED_ERROR(5204, "该记录信息不存在!"),
        UNABLE_VIEW_ERROR(5205, "无法查看该记录信息!"),
        UNABLE_DELETE_ERROR(5206, "无法删除该记录信息!"),
        ULTRA_VIRES_ERROR(5207, "您已越权,无法操作该记录信息!"),
        ALREADY_EXISTS_ERROR(5208, "已存在该记录!"),

        NOT_FOUND_ERROR(404, "路径不存在，请检查路径是否正确!"),
        SUCCESS(0, "成功!"),
        ERROR(500, "未知异常，请联系管理员!");

        private int code;
        private String message;

        StatusCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }
        public String getMessage() {
            return message;
        }
        public static StatusCode getStatusCode(int code) {
            for (StatusCode statusCode: StatusCode.values()) {
                if (statusCode.getCode() == code) {
                    return statusCode;
                }
            }
            return null;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleTaskStatus {
        /**
         * 正常
         */
        NORMAL(1),
        /**
         * 暂停
         */
        PAUSE(0);

        private int value;

        ScheduleTaskStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
