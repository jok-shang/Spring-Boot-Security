package com.boot.token;

import com.boot.token.domain.Menu;
import com.boot.token.domain.User;
import com.boot.token.mapper.MenuMapper;
import com.boot.token.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 尚智江
 * @Date 2023/5/12 22:55
 */
@SpringBootTest
public class Test {


    @Autowired
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @org.junit.jupiter.api.Test
    public void UserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @org.junit.jupiter.api.Test
    public void Password(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 密码加密
        String encode = passwordEncoder.encode("1234");
        String encode1 = passwordEncoder.encode("1234");
        System.out.println(encode+","+encode1);
        // 判断密码是否正确
        boolean matches = passwordEncoder.matches("1234", "$2a$10$tDLqkpDaWBvMkItm21uey.jrDyQ5uPi9oFBNyx9pz/vy9M8ikdjjC");
        System.out.println(matches);
    }

    @org.junit.jupiter.api.Test
    public void aaa(){
        List<String> list = menuMapper.selectPermsByUserId(2L);
        System.out.println(list);
    }

}
