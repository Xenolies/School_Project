package com.example.boot_project.config;

import com.example.boot_project.common.JWTInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new  JWTInterceptors())
                .addPathPatterns("/**")
                .excludePathPatterns("/ym_server/user/parse")
                .excludePathPatterns("/ym_server/user/login");  // 所有用户都放行
    }
}
