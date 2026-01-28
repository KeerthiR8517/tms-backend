package com.test.tms_backend.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;


public class JwtUtil {

    // Must be AT LEAST 32 characters for HS256
    private static final String SECRET = "THIS_IS_A_VERY_LONG_SECRET_KEY_FOR_TMS_PROJECT_12345";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(String role) {
        return Jwts.builder()
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String getRoleFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}