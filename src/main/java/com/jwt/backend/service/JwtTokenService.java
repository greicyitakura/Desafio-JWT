package com.jwt.backend.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    private final String secretKey = "chave-secreta";

    public String generateJwtToken(String name, String role, String seed) {
        Claims claims = Jwts.claims().setSubject("jwtChallenge");
        claims.put("Name", name);
        claims.put("Role", role);
        claims.put("Seed", seed);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
