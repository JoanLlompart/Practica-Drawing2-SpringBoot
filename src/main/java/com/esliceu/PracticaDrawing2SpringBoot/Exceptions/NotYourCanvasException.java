package com.esliceu.PracticaDrawing2SpringBoot.Exceptions;

public class NotYourCanvasException extends Exception{
    public NotYourCanvasException(String message) {
        super(message);
    }

    public void getMessage(String errorAlEliminarElLienzo) {
        System.out.println(errorAlEliminarElLienzo);
    }
}
