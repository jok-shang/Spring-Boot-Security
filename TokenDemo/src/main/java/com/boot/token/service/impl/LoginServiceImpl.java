package com.boot.token.service.impl;

import com.boot.token.domain.LoginUser;
import com.boot.token.domain.ResponseResult;
import com.boot.token.domain.User;
import com.boot.token.service.LoginService;
import com.boot.token.utils.JwtUtil;
import com.boot.token.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

/**
 * @auther 尚智江
 * @Date 2023/5/13 20:45
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authenticate进行认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // 如果认证没通过，给出相应提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        // 如果认证通过，使用userid生成一个jwt jwt存入ResponseResult进行返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把完整的用户信息存入redis userid作为key返回
        redisCache.setCacheObject("login:" + userId,loginUser);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
//        System.out.println("获取登录redis:"+redisCache.getCacheObject(userId));
        return new ResponseResult(200,"登录成功",map);
    }

    @Override
    public ResponseResult logout() {
        // 获取SecurityContextHolder中的userid
        UsernamePasswordAuthenticationToken authentication =(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200,"注销成功");
    }
}
