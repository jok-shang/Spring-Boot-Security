package spring.boot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
 
/**
 * @author 尚智江
 * @Date 2023/5/20 16:08
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationSuccessHandler successHandler;
    @Resource
    private AuthenticationFailureHandler failureHandler;
    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;
    // 认证成功处理器
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 配置认证成功处理器
                .successHandler(successHandler)
                // 配置认证失败处理器
                .failureHandler(failureHandler);
        http.logout()
                .logoutSuccessHandler(logoutSuccessHandler);
        http.authorizeRequests().anyRequest().authenticated();
    }
}
