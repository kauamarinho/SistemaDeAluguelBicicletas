package org.example.domain.vo;

import org.example.domain.exception.InvalidEmailException;

import java.util.regex.Pattern;

/**
 * Value Object representing a valid e-mail address.
 * Validation happens in the constructor, ensuring the rule
 * cannot be bypassed by any input path.
 */
public final class Email {

    private static final Pattern PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final String value;

    public Email(String raw) {
        if (raw == null || !PATTERN.matcher(raw).matches()) {
            throw new InvalidEmailException("Invalid email.");
        }
        this.value = raw;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
