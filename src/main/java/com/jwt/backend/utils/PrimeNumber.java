package com.jwt.backend.utils;


import org.springframework.stereotype.Component;

@Component
public class PrimeNumber {

    public boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
