package com.sofka.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class exceptionHandler {
    @ExceptionHandler(value = {exceptionReq.class})
    public ResponseEntity<Object> handleApiRequestException(exceptionReq e){
        // Crear mensaje clase formateado en base a una excepcion
        exceptionModel apiException = new exceptionModel(
                e.getMessage(),
                e.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, e.getHttpStatus());

    }
}
