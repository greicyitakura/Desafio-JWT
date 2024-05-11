package com.jwt.backend.utils;

public class SizeCount {
    public boolean has256Characters(String data) {
        return !data.isEmpty() && !data.isBlank() && data.length() <= 256;
    }
}
