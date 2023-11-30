package com.esliceu.PracticaDrawing2SpringBoot.Exceptions;

import java.sql.SQLException;

public class NotFindCanvasException extends SQLException {
    public NotFindCanvasException(String message) {
        super(message);
    }
}
