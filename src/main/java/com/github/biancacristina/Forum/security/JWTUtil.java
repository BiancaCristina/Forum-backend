package com.github.biancacristina.Forum.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {
        // Generate the token

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Define quando o token vai expirar baseado no tempo do sistema + tempo de expiracao
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

}
