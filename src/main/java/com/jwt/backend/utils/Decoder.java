package com.jwt.backend.utils;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Decoder { private static final String EMPTY_PAYLOAD = "{\"Role\":\"\",\"Seed\":\"\",\"Name\":\"\"}";

    public String decodePayload(String encodedData) {
        if (encodedData == null || encodedData.trim().isEmpty()) {
            throw new IllegalArgumentException("Os dados codificados estão vazios ou em branco.");
        }

        String payload = decodeBase64Payload(encodedData);
        return isStringJsonValid(payload) ? payload : EMPTY_PAYLOAD;
    }

    private String decodeBase64Payload(String encodedData) {
        try {
            return new String(Base64.getDecoder().decode(encodedData.split("\\.")[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Os dados codificados estão em um formato inválido.");
        }
    }

    private boolean isStringJsonValid(String payload) {
        if (payload == null || payload.trim().isEmpty()) {
            return false;
        }

        try {
            new JSONObject(payload);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
