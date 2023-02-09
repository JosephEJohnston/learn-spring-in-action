package com.noob.tacocloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 所有的配置类都可以实现 WebMvcConfigurer
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 直接声明控制器，不需要创建 Controller
        // registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
