package com.sofka.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorException extends RuntimeException {
    private HttpStatus status = null;
    private Object data = null;
    private FieldError errors = null;

    public CustomErrorException() {
        super();
    }

    public CustomErrorException(
            String message) {
        super(message);
    }

    public CustomErrorException(
            HttpStatus status,
            String message) {
        this(message);
        this.status = status;
    }

    public CustomErrorException(
            HttpStatus status,
            String message,
            FieldError errors) {
        this(
                status,
                message);
        this.errors = errors;
    }

    public CustomErrorException(
            HttpStatus status,
            String message,
            Object data) {
        this(
                status,
                message);
        this.data = data;
    }

    public static String getValidationMessage(FieldError error) {


            FieldError fieldError = (FieldError) error;
            String className = fieldError.getObjectName();
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();

            return String.format("_ERR: %s.%s %s, pero estaba en estado [%s]", className, property, message,
                    invalidValue);

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrors() {
        String className = this.errors.getObjectName();
        String property = this.errors.getField();
        Object invalidValue = this.errors.getRejectedValue();
        String message = this.errors.getDefaultMessage();

        return String.format("_ERR: %s.%s %s, pero estaba en estado %s", className, property, message, invalidValue);
    }

    public void setErrors(FieldError errors) {
        this.errors = errors;
    }

}
