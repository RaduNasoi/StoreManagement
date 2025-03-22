package com.example.StoreManagement.util;

import com.example.StoreManagement.exceptions.TokenExpiredException;
import com.example.StoreManagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.StoreManagement.util.StoreManagementConstants.*;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "aVeryLongSecretKeyThatIsAVeryLongSecretKey!@#%";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(USERNAME, user.getUsername())
                .claim(ROLE, user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 15 minutes
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new TokenExpiredException(TOKEN_EXPIRED);
        }
    }
}
