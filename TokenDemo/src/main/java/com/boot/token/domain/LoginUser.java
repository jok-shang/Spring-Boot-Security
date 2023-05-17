package com.boot.token.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther 尚智江
 * @Date 2023/5/12 23:48
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;
    private List<String> permissions;
    // 存储权限信息
    @JSONField(serialize = false)// 不序列化到redis中
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    // 存储SpringSecurity所需要的权限信息的集合
    // 返回权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null){
            return authorities;
        }
        // 把permissions中的string类型的权限信息封装成SimpleGrantedAuthority对象

        /*
         * 第一种方法循环list集合拿到权限信息
         */
//        authorities = new ArrayList<>();
//        for (String permission : permissions){
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            authorities.add(authority);
//        }
        /*
        第二种方法输入流  集合转换
         */
        // 把permission中的字符串类型的权限信息转换成GrantedAuthority对象存入到authorities中
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println(user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println(user.getUserName());
        return user.getUserName();
    }

    // 判断是否没过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 是否没有超时
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
