package org.example.error;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException(String message) {
        super(message);
    }
}
