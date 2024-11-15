package com.nutrimedica.nutrimedica_api.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

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
}
