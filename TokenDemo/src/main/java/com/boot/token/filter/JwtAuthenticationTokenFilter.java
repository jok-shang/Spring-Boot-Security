package com.boot.token.filter;

import com.boot.token.domain.LoginUser;
import com.boot.token.utils.JwtUtil;
import com.boot.token.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @auther 尚智江
 * @Date 2023/5/13 21:54
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)){
            // 放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        // 解析token
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        // 从redis中获取用户信息
        String redisKey = "login:" + userId;
//        System.out.println(redisCache.getCacheObject(redisKey));

        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        System.out.println("访问"+loginUser);
        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        // 存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken u =
                new UsernamePasswordAuthenticationToken(loginUser,null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(u);
        // 放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
