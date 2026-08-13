package org.example.domain.vo;

import org.example.domain.exception.CpfInvalidoException;

/**
 * Value Object que representa um CPF valido.
 * A validacao acontece no construtor: nao existe forma de criar
 * um Cliente (ou qualquer outra entidade) com um CPF invalido,
 * independente do caminho de entrada usado (service, teste, import, etc).
 */
public final class Cpf {

    private final String valor;

    public Cpf(String bruto) {
        if (bruto == null) {
            throw new CpfInvalidoException("CPF nao pode ser nulo.");
        }

        String normalizado = bruto.replace(".", "").replace("-", "");

        if (normalizado.length() != 11) {
            throw new CpfInvalidoException("CPF deve possuir 11 numeros.");
        }

        for (int i = 0; i < normalizado.length(); i++) {
            if (!Character.isDigit(normalizado.charAt(i))) {
                throw new CpfInvalidoException("CPF deve conter apenas numeros.");
            }
        }

        this.valor = normalizado;
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
        if (!(o instanceof Cpf other)) return false;
        return valor.equals(other.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }
}
