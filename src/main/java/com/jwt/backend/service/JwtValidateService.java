package com.jwt.backend.service;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jwt.backend.domain.JwtToken;
import com.jwt.backend.utils.CharacterChecker;
import com.jwt.backend.utils.Decoder;
import com.jwt.backend.utils.PrimeNumber;
import com.jwt.backend.utils.RoleChecker;
import com.jwt.backend.utils.SizeCount;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
public class JwtValidateService {

    public String validateJwtPayload(Object jwtToken) {
        if (jwtToken == null) {
            throw new IllegalArgumentException("O token JWT não pode ser nulo.");
        }

        String decodedToken;
        if (jwtToken instanceof JwtToken) {
            decodedToken = new Decoder().decodePayload(((JwtToken) jwtToken).getJwtWebToken());
        } else if (jwtToken instanceof String) {
            decodedToken = new Decoder().decodePayload((String) jwtToken);
        } else {
            throw new IllegalArgumentException("O payload do JWT deve ser uma String.");
        }

        if (!isJsonString(decodedToken)) {
            throw new IllegalArgumentException("Token JWT inválido.");
        }

        Map<String, String> decodedMap = decodeToMap(decodedToken);

        return decodedMap.size() <= 3 &&
                validateRole(decodedMap.get("Role")) &&
                validateSeed(decodedMap.get("Seed")) &&
                validateName(decodedMap.get("Name")) ? "verdadeiro" : "falso";
    }

    private boolean isJsonString(String jsonString) {
        try {
            JsonParser.parseString(jsonString);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    private Map<String, String> decodeToMap(String decodedToken) {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        return gson.fromJson(decodedToken, mapType);
    }

    private boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        boolean nameContainsDigit = new CharacterChecker().hasOnlyLettersAndSymbols(name);
        return nameContainsDigit && new SizeCount().has256Characters(name);
    }

    private boolean validateRole(String role) {
        if (role == null || role.isEmpty()) {
            return false;
        }
        return new RoleChecker().isValidRole(role);
    }

    private boolean validateSeed(String seed) {
        if (seed == null || seed.isEmpty()) {
            return false;
        }
        return new PrimeNumber().isPrimeNumber(Integer.parseInt(seed));
    }
}
