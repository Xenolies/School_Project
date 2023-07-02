package com.example.boot_project;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;

public class jwtTest {
    @Test
    void contextLoads() {

        HashMap<String, Object> map = new HashMap<>();


        String token = JWT.create()
                .withHeader(map) // header可以不写，因为默认值就是它
                .withClaim("userId", 21)  //payload
                .withClaim("username", "xiaoshuang")
                .sign(Algorithm.HMAC256("XIAOSHUANG"));//签名

        System.out.println(token);
    }

    @Test
    public void test(){
        // 通过签名生成验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("XIAOSHUANG")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjIxLCJ1c2VybmFtZSI6InhpYW9zaHVhbmcifQ.qorp-S_0VpZd-awzhNmRwXTWR0hrXOl0ZVx35KQsNto");
        System.out.println(verify.getClaim("userId"));
        System.out.println(verify.getClaim("username"));
        System.out.println("令牌过期时间："+verify.getExpiresAt());

    }


}
