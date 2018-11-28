package org.clm;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Ccc
 * @date 2018/11/21 0021 下午 7:47
 */
public class Jwttest {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("1")
                .setSubject("陈文思")
                //签发时间
                .setIssuedAt(new Date())
                //设置签名和密钥
                .signWith(SignatureAlgorithm.HS256,"itcast");
        System.out.println(builder.compact());
    }
}
