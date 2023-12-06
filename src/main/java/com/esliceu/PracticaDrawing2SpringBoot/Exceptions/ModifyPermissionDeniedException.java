package com.esliceu.PracticaDrawing2SpringBoot.Exceptions;

public class ModifyPermissionDeniedException extends RuntimeException{
    @Override
    public String getMessage() {
        return "No tienes permisos para modificar este canvas";
    }
}
