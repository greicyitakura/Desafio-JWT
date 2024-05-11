package com.jwt.backend.service;

import com.jwt.backend.domain.JwtToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtValidateServiceTest {

    private JwtValidateService jwtValidateService;

    @BeforeEach
    public void setUp() {
        jwtValidateService = new JwtValidateService();
    }

    @Test
    void itShoulTestGenerateValidJwtToken() {
        JwtTokenService jwtTokenService = new JwtTokenService();
        String jwtToken = jwtTokenService.generateJwtToken("Toninho Araujo", "Admin", "7841");
        assertTrue(jwtToken != null && !jwtToken.isEmpty());
    }

    @Test
    void itShouldTestInvalidToken() {
        assertThrows(IllegalArgumentException.class, () -> {
            jwtValidateService.validateJwtPayload(new Object());
        });
    }

    @Test
    void itshouldBeAnInstanceOfString() {
        JwtToken jwtPayloadToken = new JwtToken();
        jwtPayloadToken.setJwtWebToken("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
        Assertions.assertInstanceOf(String.class, this.jwtValidateService.validateJwtPayload(jwtPayloadToken));
    }

    @Test
    void itShoulTestCase1() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        assertEquals("verdadeiro", jwtValidateService.validateJwtPayload(jwtToken));
    }

    @Test
    void itShouldReturnFalseFor3() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
        assertEquals("falso", jwtValidateService.validateJwtPayload(jwtToken));
    }

    @Test
    void itShouldReturnFalseFor4() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
        assertEquals("falso", jwtValidateService.validateJwtPayload(jwtToken));
    }
}