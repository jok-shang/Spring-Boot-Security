package com.boot.token.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 尚智江
 * @Date 2023/5/20 13:44
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    // SpringBoot允许跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
//                .allowedOriginPatterns("*")// 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("Get","POST","DELETE","PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 允许跨域的时间
                .maxAge(3600);

    }
}
