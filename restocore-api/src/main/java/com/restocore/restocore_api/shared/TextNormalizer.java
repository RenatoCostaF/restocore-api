package com.restocore.restocore_api.shared;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TextNormalizer {

    public String normalizeToLowerTrim(String value) {
        return value == null ? null : value.trim().toLowerCase(Locale.ROOT);
    }
}
