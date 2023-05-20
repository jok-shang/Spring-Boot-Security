package com.boot.token.config;

import com.boot.token.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/5/13 0:22
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)// 开启注解权限的功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    // 创建BCryptPasswordEncoder注入容器 用户密码加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问，未登录可以访问，登录后不能访问
                .antMatchers("/user/login").anonymous()
                // （基于配置的权限访问）必须具有权限才能进行跨域操作
                .antMatchers("/testCors").hasAuthority("system:dept:list")
                // .permitAll()表示登录/未登录都能访问
                // .anonymous()表示匿名访问，未登录可以访问，登录后不能访问
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();// 表示任意用户登录后都可以访问
        // 添加过滤器顺序
        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器
        http.exceptionHandling()
                // 认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权失败处理器
                .accessDeniedHandler(accessDeniedHandler);

        // 设置允许跨域
        http.cors();
    }


}
