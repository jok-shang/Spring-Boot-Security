package com.jwt.token.interceptor;

import com.jwt.token.domin.ResponseResult;
import com.jwt.token.util.WebUtils;
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
        String token = request.getHeader("token");
        System.out.println("请求头中携带的token信息为：" + token);
        // 判断请求头中是否携带token检查token是否过期
        if (ObjectUtils.isEmpty(token)){
            WebUtils.renderString(response,"token为空");
            return false;
        }

        return true;
    }
}
