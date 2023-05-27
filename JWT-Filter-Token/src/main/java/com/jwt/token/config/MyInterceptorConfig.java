package com.jwt.token.config;

import com.jwt.token.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author 尚智江
 * @CreateDate 2023/5/27 23:17
 */
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {

    // 添加拦截路径
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                // 添加拦截路径
                .addPathPatterns("/**")
                // 添加不需要拦截的路径
                .excludePathPatterns("/user/login","/user/register");
    }
}
