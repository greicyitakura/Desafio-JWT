package com.jwt.backend.utils;

import org.springframework.stereotype.Component;

@Component
public class CharacterChecker {

    public boolean hasNoSpecialCaracteres(String stringWithNumber) {
        return stringWithNumber.matches("^[a-zA-Z\\p{Punct}\\sçÇáàãâéèêíìóòõôúùûüÁÀÃÂÉÈÊÍÌÓÒÕÔÚÙÛÜ]*$");
    }
}