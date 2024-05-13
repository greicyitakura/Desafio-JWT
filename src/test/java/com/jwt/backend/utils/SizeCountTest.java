package com.jwt.backend.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeCountTest {
    private SizeCount sizeCount;

    @BeforeEach
    void setUp() {
        sizeCount = new SizeCount();
    }

    @Test
    void itShouldTestValidData() {
        String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae felis in nisi vestibulum faucibus.";
        assertTrue(sizeCount.has256Characters(data));
    }

    @Test
    void itShouldTestExceedsMaxLength() {
        String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum., Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae felis in nisi vestibulum faucibus. Nam scelerisque felis ut turpis lacinia volutpat.";
        assertFalse(sizeCount.has256Characters(data));
    }

    @Test
    void itShouldTestContainsNumbers() {
        String data = "Lorem ipsum dolor sit amet, 123456 consectetur adipiscing elit.";
        assertTrue(sizeCount.has256Characters(data));
    }
}