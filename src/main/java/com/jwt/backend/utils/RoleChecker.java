package com.jwt.backend.utils;

import org.springframework.stereotype.Component;

@Component
public class RoleChecker {
    public enum Role {
        ADMIN,
        MEMBER,
        EXTERNAL
    }

    public boolean isValidRole(Role role) {
        return role != null;
    }
}