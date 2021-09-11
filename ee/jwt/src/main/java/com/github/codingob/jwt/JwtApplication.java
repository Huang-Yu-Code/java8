package com.github.codingob.jwt;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;


/**
 * @author HuangYu
 */
public class JwtApplication {
    private final static long TIME = 1000 * 60 * 60;

    private final static String SIGNATURE = "admin";

    public static String getToken() {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuer("admin")
                .setAudience("users")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .claim("username", "tom")
                .claim("role", "admin")
                .setSubject("auth")
                .setExpiration(new Date(System.currentTimeMillis() + TIME))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                .compact();
    }

    public static boolean valid(String token) {
        try {
            Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("验证失败");
            return false;
        }
    }

    public static Claims getTokenInfo(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(SIGNATURE).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    public static String getTokenInfo(String token, String key) {
        Claims claims = getTokenInfo(token);
        return claims.get(key).toString();
    }

    public static void main(String[] args) {
        String token = getToken();
        System.out.println(valid(token));
        System.out.println(valid("123"));
        System.out.println(token);
        System.out.println(getTokenInfo(token, "username"));

    }

}
