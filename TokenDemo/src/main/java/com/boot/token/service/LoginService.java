package com.boot.token.service;

import com.boot.token.domain.ResponseResult;
import com.boot.token.domain.User;
import org.springframework.stereotype.Service;

/**
 * @auther 尚智江
 * @Date 2023/5/13 20:45
 */
@Service
public interface LoginService {


    ResponseResult login(User user);

    ResponseResult logout();
}
