package org.example.domain.vo;

import org.example.domain.exception.EmailInvalidoException;

import java.util.regex.Pattern;

/**
 * Value Object que representa um e-mail valido.
 * A validacao acontece no construtor, garantindo que a regra
 * nao possa ser burlada por nenhum caminho de entrada.
 */
public final class Email {

    private static final Pattern PADRAO = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final String valor;

    public Email(String bruto) {
        if (bruto == null || !PADRAO.matcher(bruto).matches()) {
            throw new EmailInvalidoException("Email invalido.");
        }
        this.valor = bruto;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email other)) return false;
        return valor.equals(other.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }
}
