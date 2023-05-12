package com.boot.token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.token.domain.LoginUser;
import com.boot.token.domain.User;
import com.boot.token.mapper.UserMapper;
//import com.boot.token.service.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @auther 尚智江
 * @Date 2023/5/12 23:33
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user+"----");
        // 如果没有查询到用户就抛异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //TODO 查询用户权限信息

        // 如果查到吧数据封装成UserDetails
        return new LoginUser(user);
    }


//    // 查询用户信息
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//

//    }
}
