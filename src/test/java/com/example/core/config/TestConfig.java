package com.example.core.config;

import com.example.core.i18n.Language;

public final class TestConfig {
    private TestConfig() {}

    public static Language language() {
        String lang = System.getProperty("lang", "DE").trim();
        return Language.from(lang);
    }
}
