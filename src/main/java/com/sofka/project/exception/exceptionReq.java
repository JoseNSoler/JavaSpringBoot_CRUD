package com.sofka.project.exception;

import org.springframework.http.HttpStatus;

public class exceptionReq extends RuntimeException {
    private HttpStatus httpStatus;

    public exceptionReq(String message) {
        super(message);
    }

    public exceptionReq(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }


    public exceptionReq(String message, Throwable cause){
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
