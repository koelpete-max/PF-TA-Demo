package com.example.core.i18n;

import java.util.Arrays;

public enum Language {
    EN("English"),
    FR("Français"),
    DE("Deutsch"),
    IT("Italiano");

    private final String displayName;

    Language(String displayName) { this.displayName = displayName; }

    public String displayName() { return displayName; }

    public static Language from(String value) {
        return Arrays.stream(values())
                .filter(l -> l.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown language: " + value));
    }
}