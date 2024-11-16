package com.nutrimedica.nutrimedica_api.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtil {

    private static final String SECRET = "sua-chave-secreta-super-segura-para-o-jwt-nutrimedica";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Long id) {
        long expirationTime = 86400000;
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                   .claim("id", id)
                   .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                   .setIssuedAt(now)
                   .setExpiration(expirationDate)
                   .compact();
        }

    public static Long extractUserId(String token) {
        Claims claims = validateToken(token);
        if (claims != null) {
            return claims.get("id", Long.class);
        }
        return null;
    }

    public static Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                       .setSigningKey(SECRET_KEY)
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }
}
