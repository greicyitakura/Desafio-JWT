package com.jwt.backend.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleChecker {
    private final List<String> roles = List.of("Admin", "Member", "External");

    public boolean isValidRole(String role) {
        return roles.stream().anyMatch(r -> !role.isEmpty() && !role.isBlank() && role.equalsIgnoreCase(r));
    }
}
