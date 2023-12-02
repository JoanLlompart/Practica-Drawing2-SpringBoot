package com.esliceu.PracticaDrawing2SpringBoot.Exceptions;
public class InvalidPermissionTypeException extends Exception {

    public InvalidPermissionTypeException() {
        super("El tipo de permiso no es válido");
    }

    public InvalidPermissionTypeException(String message) {
        super(message);
    }
}
