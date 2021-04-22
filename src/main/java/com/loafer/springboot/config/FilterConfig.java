package com.loafer.springboot.config;

import com.loafer.springboot.common.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter配置
 *
 * @author Loafer
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean xssFilterRegister() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 设置系统过滤器 (setFilter就是你所定义的过滤器filter类)
        registration.setFilter(new XssFilter());
        // 过滤所有路径
        registration.addUrlPatterns("/*");
        // 过滤器名称
        registration.setName("XssFilter");
        // 优先级
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

}
