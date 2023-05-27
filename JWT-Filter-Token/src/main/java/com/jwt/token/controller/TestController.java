package com.jwt.token.controller;

import com.jwt.token.domin.ResponseResult;
import com.jwt.token.domin.User;
import com.jwt.token.util.JWTUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 尚智江
 * @CreateDate 2023/5/27 23:22
 */
@RestController
public class TestController {




    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        String username = "aaa";
        String password = "aaa";
        System.out.println(user.getUsername()+","+user.getPassword());
        if (user.getUsername().equals(username) && user.getPassword().equals(password)){
            user.setId(1);
            String token = JWTUtil.creatToken(user);
            return new ResponseResult(200,"登录成功",token);
        }
        return new ResponseResult(201,"失败");
    }

    @GetMapping("/index")
    public ResponseResult index(){
        return new ResponseResult(200,"访问成功","index");
    }
}
