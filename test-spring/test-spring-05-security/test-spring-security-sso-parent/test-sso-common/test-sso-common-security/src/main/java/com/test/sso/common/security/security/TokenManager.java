package com.test.sso.common.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {
    //token有效时长
    private long tokenEcpiration = 24 * 60 * 60 * 1000;
    //编码秘钥
    private String tokenSignKey = "123456";

    //1 使用jwt根据用户名生成token
    public String createToken(String username) {
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenEcpiration))
                // 签名
                .sign(Algorithm.HMAC256(tokenSignKey));
        return token;
    }

    //2 根据token字符串得到用户信息
    public String getUserInfoFromToken(String token) {
        String userinfo = JWT.decode(tokenSignKey).getSubject();
        return userinfo;
    }

    //3 删除token
    public void removeToken(String token) {
    }
}
