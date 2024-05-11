package com.jwt.backend.utils;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Decoder {
    private static final String EMPTY_PAYLOAD = "{\"Role\":\"\",\"Seed\":\"\",\"Name\":\"\"}";

    public String decodePayload(String encodedData) {
        if (encodedData.isEmpty() && encodedData.isBlank()) {
            throw new IndexOutOfBoundsException("ERROR:: Index out of bounds: encodedData: " + encodedData.length());
        }

        try {
            String payload = new String(Base64.getDecoder().decode(encodedData.split("\\.")[1]));
            return isStringJsonValid(payload) ? payload : EMPTY_PAYLOAD;
        } catch (IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException("ERROR:: Index out of bounds: encodedData: " + encodedData.length());
        }
    }

    private boolean isStringJsonValid(String payload) {
        try {
            if (payload.isBlank()) {
                new JSONObject(new JSONTokener(payload));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
