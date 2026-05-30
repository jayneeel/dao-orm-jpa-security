package com.example.spring_security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {


    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000*60*3)).signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private static Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode("9f7a3e2b1c5d6e8f0a1b2c3d4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d2e3f");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
