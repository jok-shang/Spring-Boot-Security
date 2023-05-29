package com.jwt.token.util;

import com.jwt.token.domin.User;
import io.jsonwebtoken.*;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author 尚智江
 * @CreateDate 2023/5/27 19:44
 */

/*
    jwt工具类
 */
public class JWTUtil {

    // 有效期设置位七天
    private static long time = 1000*60*60*24*7;
//    private static long time = 1000*10;
    private static final String APPSECRET = "admin";


    /**
     * 生成token
     * @param user
     * @return
     */
    public static String creatToken(User user){
        if (ObjectUtils.isEmpty(user)){
            return null;
        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder
                // 设置头部信息
                .setHeaderParam("type","JWT")
                // 设置加密方式
                .setHeaderParam("alg","HS256")
                // 负载（需要在token中存在的用户信息）
                .claim("uid",user.getId())
                .setSubject("APPSECRET")
                // 设置过期时间 系统当前时间加上 token的有效时间
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,APPSECRET)
                // 拼接token
                .compact();
    }

    /**
     * 检查token是否过期
     * @param token
     * @return
     */
    public static boolean checkToken(String token){
        if (ObjectUtils.isEmpty(token)){
            return false;
        }
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token);
            System.out.println("claimsJws:"+claimsJws);
            System.out.println("toString:"+claimsJws.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        User user = new User(1,"222","www");
        String s = creatToken(user);
        System.out.println("生成的token值为："+s);
//        for (int i = 10;i > 0;i--){
//            Thread.sleep(1000);
//        }
        System.out.print("检查token是否过期：");
        boolean b = checkToken(s);
        System.out.print(b);

    }


}
