package com.jwt.token.interceptor;

import com.jwt.token.domin.ResponseResult;
import com.jwt.token.util.JWTUtil;
import com.jwt.token.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
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
        //如果是预检请求，手动加上请求状态200

        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        String token = request.getHeader("token");
        System.out.println("请求头中携带的token信息为：" + token);
//        System.out.println("token过期"+JWTUtil.checkToken(token));
        // 判断请求头中是否携带token检查token是否为空
        if (ObjectUtils.isEmpty(token)){
            WebUtils.renderString(response,"token为空");
            return false;
        }
        if (!JWTUtil.checkToken(token)){
            System.out.println("token过期");
            WebUtils.renderString(response,"token过期");
            return false;
        }else
        return true;
    }
}
