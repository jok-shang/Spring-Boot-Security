package com.boot.token.handle;

import com.alibaba.fastjson.JSON;
import com.boot.token.domain.ResponseResult;
import com.boot.token.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 尚智江
 * @Date 2023/5/19 13:26
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        // 权限异常
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(),"权限不足");
        String json = JSON.toJSONString(responseResult);
        // 处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
}
