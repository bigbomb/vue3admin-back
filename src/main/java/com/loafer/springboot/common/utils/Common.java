package com.loafer.springboot.common.utils;

/**
 * 通用业务工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class Common {
    /**
     * 判断是否是超级管理员
     * @param userId
     * @return
     */
    public static boolean isSuper(Long userId) {
        boolean isSuper = false;
        for (Long id : Constant.SUPER_ADMINISTRATOR) {
            if (id.equals(userId)) {
                isSuper = true;
                break;
            }
        }
        return isSuper;
    }
}
