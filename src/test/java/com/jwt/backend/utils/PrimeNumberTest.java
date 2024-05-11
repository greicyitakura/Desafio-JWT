package com.jwt.backend.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberTest {

    private PrimeNumber primeNumber;

    @BeforeEach
    void setUp() {
        primeNumber = new PrimeNumber();
    }

    @Test
    void itShouldTestPrimeNumber() {
        int data = 7;
        assertTrue(primeNumber.isPrimeNumber(data));
    }

    @Test
    void itShouldTestNotPrimeNumber() {
        int data = 6;
        assertFalse(primeNumber.isPrimeNumber(data));
    }

    @Test
    void itShouldTestZero() {
        int zero = 0;
        assertFalse(primeNumber.isPrimeNumber(zero));
    }

}