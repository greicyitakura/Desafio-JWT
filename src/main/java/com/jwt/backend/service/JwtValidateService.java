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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
public class JwtValidateService {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidateService.class);
    private final RoleChecker roleChecker;

    public JwtValidateService(RoleChecker roleChecker) {
        this.roleChecker = roleChecker;
    }

    public String validateJwtPayload(Object jwtToken) {
        if (jwtToken == null) {
            logger.error("O token JWT não pode ser nulo.");
            throw new IllegalArgumentException("O token JWT não pode ser nulo.");
        }

        String decodedToken;
        if (jwtToken instanceof JwtToken) {
            decodedToken = new Decoder().decodePayload(((JwtToken) jwtToken).getJwtWebToken());
        } else if (jwtToken instanceof String) {
            decodedToken = new Decoder().decodePayload((String) jwtToken);
        } else {
            logger.error("O payload do JWT deve ser uma String.");
            throw new IllegalArgumentException("O payload do JWT deve ser uma String.");
        }

        if (!isJsonString(decodedToken)) {
            logger.error("Token JWT inválido: {}", decodedToken);
            throw new IllegalArgumentException("Token JWT inválido.");
        }

        Map<String, String> decodedMap = decodeToMap(decodedToken);

        boolean validationResult = decodedMap.size() <= 3 &&
                validateRole(decodedMap.get("Role")) &&
                validateSeed(decodedMap.get("Seed")) &&
                validateName(decodedMap.get("Name"));

        logger.info("Resultado da validação do JWT: {}", validationResult);

        return validationResult ? "verdadeiro" : "falso";
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
        boolean nameContainsDigit = new CharacterChecker().hasNoSpecialCaracteres(name);
        return nameContainsDigit && new SizeCount().has256Characters(name);
    }

    public boolean validateRole(String role) {
        RoleChecker.Role roleEnum = RoleChecker.Role.valueOf(role.toUpperCase());
        return roleChecker.isValidRole(roleEnum);
    }

    private boolean validateSeed(String seed) {
        if (seed == null || seed.isEmpty()) {
            return false;
        }
        return new PrimeNumber().isPrimeNumber(Integer.parseInt(seed));
    }
}

