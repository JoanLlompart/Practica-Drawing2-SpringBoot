package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.ModifyPermissionDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    @ExceptionHandler(ModifyPermissionDeniedException.class)
    public ResponseEntity<String> handleModifyPermissionDeniedException(ModifyPermissionDeniedException e) {
        // Aquí el código para crear la respuesta con el mensaje de error y el código de estado
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
