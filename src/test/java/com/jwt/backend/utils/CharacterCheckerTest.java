package com.jwt.backend.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCheckerTest {

    private CharacterChecker characterChecker;
    @BeforeEach
    void setUp() {
        characterChecker = new CharacterChecker();
    }
    @Test
    void itShouldTestNoSpecialCharactersValidData() {
        String data = "Lorem ipsum dolor sit amet";
        assertTrue(characterChecker.hasNoSpecialCaracteres(data));
    }

    @Test
    void itShouldTestNoSpecialCharactersWithNumbers() {
        String data = "Lorem ipsum dolor sit amet 123";
        assertFalse(characterChecker.hasNoSpecialCaracteres(data));
    }

    @Test
    void itShouldTestNoSpecialCharactersWithBoth() {
        String data = "Lorem ipsum dolor sit amet, 123";
        assertFalse(characterChecker.hasNoSpecialCaracteres(data));
    }

    @Test
    void itShouldTestNoSpecialCharactersBlankData() {
        String data = "";
        assertTrue(characterChecker.hasNoSpecialCaracteres(data));
    }

}