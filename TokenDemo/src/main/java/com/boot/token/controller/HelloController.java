package com.boot.token.controller;

import com.boot.token.domain.ResponseResult;
import com.boot.token.domain.User;
import com.boot.token.mapper.UserMapper;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 登录后访问接口成功
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        return "token";
    }


    /**
     * 用户携带system:dept:list的权限访问成功
     * @return
     */
    @GetMapping("/qx")
    @PreAuthorize("hasAuthority('system:dept:list')")//必须具有权限才能访问 测试用户携带test，admin权限
    // 如果为true才能访问 为false不能访问
    public String helloqx(){
        return "具有权限才能访问的接口";
    }


    /**
     * 用户未携带aaa的权限访问接口失败
     * @return
     */
    @GetMapping("exqx")
    @PreAuthorize("hasAnyAuthority('aaa')")
    public String helloexqx(){
        return "No";
    }


    // 测试跨域
    @RequestMapping("/testCors")
    public ResponseResult testCors(){
        return new ResponseResult(200,"testCOrs");
    }
}
