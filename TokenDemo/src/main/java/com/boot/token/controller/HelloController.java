package com.boot.token.controller;

import com.boot.token.domain.User;
import com.boot.token.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 尚智江
 * @Date 2023/5/7 23:36
 */
@RestController
public class HelloController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/hello")
    public String hello(){
        return "token";
    }



}
