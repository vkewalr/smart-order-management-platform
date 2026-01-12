package com.somp.user_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "my-secret-key-for-somp-not-to-be-shared-with-anyone";

    public String generateToken(String email){
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes())).compact();
    }

    public String extractEmail(String token){
        return Jwts.parser().setSigningKey(SECRET.getBytes())
                .build().parseClaimsJws(token).getBody().getSubject();
    }
}
