package com.boot.token.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @auther 尚智江
 * @Date 2023/5/13 0:22
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // 创建BCryptPasswordEncoder注入容器 用户密码加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
