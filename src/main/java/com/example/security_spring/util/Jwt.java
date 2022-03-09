package com.example.security_spring.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class Jwt {
    // Base 64 secret
    private SecretKey SECRET = Keys
            .hmacShaKeyFor("djfhdfgjkghjbdfghdsfjhfdghsdfdfdsf".getBytes(StandardCharsets.UTF_8));

    public String generateToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getUsername());
    }

    public String createToken(Map<String, Object> claims, String subject) {

        Claims payload = Jwts.claims(claims);
        payload.setSubject(subject);
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET)
                .compact();

    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) throws JwtException {
        return Jwts.parserBuilder().setSigningKey(SECRET)
                .build().parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public Boolean validateToken(String token, UserDetails user) {
        String username = extractUsername(token);
        Boolean isExpired = isTokenExpired(token);

        return ((user.getUsername().equals(username)) && !isExpired);
    }

}
