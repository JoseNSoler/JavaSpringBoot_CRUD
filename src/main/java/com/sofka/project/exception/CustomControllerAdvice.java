package com.sofka.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.NoSuchElementException;


@ControllerAdvice
class CustomControllerAdvice {

        // ...

        @ExceptionHandler(NullPointerException.class) // exception handled
        public ResponseEntity<ErrorResponse> handleNullPointerExceptions(
                        Exception e) {
                // ... potential custom logic

                HttpStatus status = HttpStatus.NOT_FOUND; // 404

                return new ResponseEntity<>(
                                new ErrorResponse(
                                                status,
                                                e.getMessage()),
                                status);
        }

        @ExceptionHandler(NoSuchElementException.class) // exception handled
        public ResponseEntity<ErrorResponse> handleNoSuchElementException(
                        Exception e) {
                // ... potential custom logic

                HttpStatus status = HttpStatus.I_AM_A_TEAPOT; // 404

                return new ResponseEntity<>(
                        new ErrorResponse(
                                status,
                                e.getMessage()),
                status);
        }

        
        @ExceptionHandler(CustomErrorException.class) // exception handled
        public ResponseEntity<ErrorResponse> handleCustomException(
                        CustomErrorException e) {
                // ... potential custom logic

                e.getStatus();
                HttpStatus status = e.getStatus(); // 404

                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                String stackTrace = stringWriter.toString();

                return new ResponseEntity<>(
                        new ErrorResponse(
                                status,
                                e.getMessage()),
                status);
        }



        // fallback method
        @ExceptionHandler(Exception.class) // exception handled
        public ResponseEntity<ErrorResponse> handleExceptions(
                        Exception e) {


                HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

                // converting the stack trace to String
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                String stackTrace = stringWriter.toString();

                return new ResponseEntity<>(
                                new ErrorResponse(
                                                status,
                                                e.getMessage(),
                                                stackTrace // specifying the stack trace in case of 500s
                                ),
                                status);
        }
}