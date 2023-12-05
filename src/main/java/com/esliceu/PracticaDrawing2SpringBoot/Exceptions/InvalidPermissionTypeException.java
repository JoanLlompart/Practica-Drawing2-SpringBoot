package com.esliceu.PracticaDrawing2SpringBoot.Exceptions;
public class InvalidPermissionTypeException extends RuntimeException {

    public InvalidPermissionTypeException() {
        super("El tipo de permiso no es válido");
    }

    @Override
    public String getMessage() {
        return "Aquest permis no es valid";
    }
}
