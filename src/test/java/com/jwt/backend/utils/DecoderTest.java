package com.jwt.backend.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    private Decoder decoder;
    @BeforeEach
    void setUp() {
        decoder = new Decoder();
    }

    @Test
    void itShouldTestValidPayload() {
        String encodedData = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"; // exemplo de dados codificados
        String expectedPayload = "{\"Role\":\"Admin\",\"Seed\":\"7841\",\"Name\":\"Toninho Araujo\"}";
        assertEquals(expectedPayload, decoder.decodePayload(encodedData));
    }

    @Test
    void itShouldTestEmptyPayload() {
        String encodedData = "";
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.decodePayload(encodedData);
        });
    }

    @Test
    void itShouldTestInvalidDecoder() {
        String encodedData = "String inválida";
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.decodePayload(encodedData);
        });
    }

    @Test
    void itShouldTestNullPayload() {
        String encodedData = null;
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.decodePayload(encodedData);
        });
    }
}