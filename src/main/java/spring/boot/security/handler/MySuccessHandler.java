package spring.boot.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 尚智江
 * @Date 2023/5/20 16:06
 */
@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 认证成功处理器
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    // 在下面的方法中实现相关逻辑
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("认证成功");
    }
}
