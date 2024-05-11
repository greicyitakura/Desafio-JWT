package com.jwt.backend.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CharacterChecker {

    public boolean hasOnlyLettersAndSymbols(String stringWithNumber) {
        if (stringWithNumber.isEmpty() && stringWithNumber.isBlank()) {
            return false;
        }
        return Pattern.compile("^[a-zA-Z\\p{Punct}\\sçÇáàãâéèêíìóòõôúùûüÁÀÃÂÉÈÊÍÌÓÒÕÔÚÙÛÜ]*$").matcher(stringWithNumber).matches();
    }
}