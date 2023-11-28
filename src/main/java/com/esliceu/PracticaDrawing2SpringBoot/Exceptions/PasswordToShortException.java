package com.esliceu.PracticaDrawing2SpringBoot.Exceptions;

public class PasswordToShortException extends RuntimeException {
    public PasswordToShortException(String message) {
        super(message);
    }
}
