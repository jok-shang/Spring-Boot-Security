package com.boot.token.handle;

import com.alibaba.fastjson.JSON;
import com.boot.token.domain.ResponseResult;
import com.boot.token.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 尚智江
 * @Date 2023/5/19 13:21
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {


    /**
     * 定义异常处理类
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        // 认证失败
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败请重新登录");
        String json = JSON.toJSONString(responseResult);
        // 处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
}
