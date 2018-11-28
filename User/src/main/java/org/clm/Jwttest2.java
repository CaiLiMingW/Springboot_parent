package org.clm;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Ccc
 * @date 2018/11/21 0021 下午 7:47
 */
public class Jwttest2 {
    public static void main(String[] args) {
        String token = ceateJwt();
        ParseJwtTest.soutJwt(token);
    }

    public static String ceateJwt(){
        long now = System.currentTimeMillis();
        long exp = now+160*1000;
        JwtBuilder builder = Jwts.builder().setId("1")
                .setSubject("陈文思")
                //签发时间
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(exp))
                //设置签名和密钥
                .signWith(SignatureAlgorithm.HS256,"itcast")
                //自定义
                .claim("username","cws")
                .claim("password",123456);

        System.out.println(builder.compact());
        return builder.compact();
    }
}
