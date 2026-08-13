package org.example.domain.exception;

public class EmailInvalidoException extends RuntimeException {

    public EmailInvalidoException(String mensagem) {
        super(mensagem);
    }
}