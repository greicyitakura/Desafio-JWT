package com.jwt.backend.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleCheckerTest {

    private RoleChecker roleChecker;

    @BeforeEach
    void setUp() {
        roleChecker = new RoleChecker();
    }

    @Test
    void itShouldTestValidRole() {
        String data = "Admin";
        assertTrue(roleChecker.isValidRole(data));
    }

    @Test
    void itShouldTestInvalidRole() {
        String data = "User";
        assertFalse(roleChecker.isValidRole(data));
    }

    @Test
    void itShouldTestBlankRole() {
        String data = "       ";
        assertFalse(roleChecker.isValidRole(data));
    }
}