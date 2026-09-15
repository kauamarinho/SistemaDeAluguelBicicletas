package org.example.domain.vo;

import org.example.domain.exception.InvalidCpfException;

/**
 * Value Object representing a valid CPF (Brazilian taxpayer ID).
 * Validation happens in the constructor: there is no way to create
 * a Customer (or any other entity) with an invalid CPF,
 * regardless of the input path used (service, test, import, etc).
 */
public final class Cpf {

    private final String value;

    public Cpf(String raw) {
        if (raw == null) {
            throw new InvalidCpfException("CPF cannot be null.");
        }

        String normalized = raw.replace(".", "").replace("-", "");

        if (normalized.length() != 11) {
            throw new InvalidCpfException("CPF must have 11 digits.");
        }

        for (int i = 0; i < normalized.length(); i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                throw new InvalidCpfException("CPF must contain only digits.");
            }
        }

        this.value = normalized;
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
        if (!(o instanceof Cpf other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
