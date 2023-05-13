package com.boot.token.controller;

import com.boot.token.domain.ResponseResult;
import com.boot.token.domain.User;
import com.boot.token.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther 尚智江
 * @Date 2023/5/13 20:43
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        // 登录
        return loginService.login(user);
    }
}
