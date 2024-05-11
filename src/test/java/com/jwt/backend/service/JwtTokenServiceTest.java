package com.jwt.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenServiceTest {
    private final String secretKey = "chave-secreta";

    @Test
    void testGenerateJwtToken() {
        JwtTokenService jwtTokenService = new JwtTokenService();
        String name = "Toninho Araujo";
        String role = "Admin";
        String seed = "7841";

        String jwtToken = jwtTokenService.generateJwtToken(name, role, seed);
        assertNotNull(jwtToken);

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();

        assertEquals("jwtChallenge", claims.getSubject());
        assertEquals(name, claims.get("Name"));
        assertEquals(role, claims.get("Role"));
        assertEquals(seed, claims.get("Seed"));
    }

}