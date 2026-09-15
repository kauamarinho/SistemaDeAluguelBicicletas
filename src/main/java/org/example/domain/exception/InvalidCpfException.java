package org.example.domain.exception;

public class InvalidCpfException extends RuntimeException {

    public InvalidCpfException(String message) {
        super(message);
    }
}
