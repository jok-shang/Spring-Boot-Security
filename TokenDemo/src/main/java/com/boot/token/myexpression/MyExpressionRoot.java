package com.boot.token.myexpression;

import com.boot.token.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 尚智江
 * @Date 2023/5/20 15:29
 */

@Component("ex")
public class MyExpressionRoot {

    /*
    自定义权限判定方法
     */
    public boolean hasAuthority(String authority){
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        // 判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }
}
