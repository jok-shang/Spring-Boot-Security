package com.jwt.token.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 尚智江
 * @CreateDate 2023/5/27 20:30
 */

@Configuration
public class JwtInterceptor implements HandlerInterceptor {

    // 重写preHandle方法


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断请求头中是否携带token检查token是否过期
        if (ObjectUtils.isEmpty(request.getHeader("token"))){
            return false;
        }
        return true;
    }
}
