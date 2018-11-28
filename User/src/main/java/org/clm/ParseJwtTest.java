package org.clm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Ccc
 * @date 2018/11/21 0021 下午 7:56
 */
public class ParseJwtTest {
    public static void main(String[] args) {

    }

    public static void soutJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey("itcast")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("==============================");
        System.out.println("Id:"+claims.getId());
        System.out.println("过期时间"+claims.getExpiration());
        System.out.println("Subject:"+claims.getSubject());
        System.out.println("IssuedAt:"+claims.getIssuedAt());
        System.out.println("自定义:"+claims.get("username"));
        System.out.println("自定义:"+claims.get("password"));
        claims.get("password");
    }
}
