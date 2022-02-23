package com.sofka.project.exception;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class exceptionValidation extends RuntimeException{
    
   private final BindingResult errors;

   public exceptionValidation(BindingResult errors) {
       this.errors = errors;
   }

   public List<String> getMessages() {
       return getValidationMessage(this.errors);
   }


   @Override
   public String getMessage() {
       return this.getMessages().toString();
   }


   //demonstrate how to extract a message from the binging result
   public static List<String> getValidationMessage(BindingResult bindingResult) {
       return bindingResult.getAllErrors()
               .stream()
               .map(exceptionValidation::getValidationMessage)
               .collect(Collectors.toList());
   }

   public static String getValidationMessage(ObjectError error) {
       if (error instanceof FieldError) {
           FieldError fieldError = (FieldError) error;
           String className = fieldError.getObjectName();
           String property = fieldError.getField();
           Object invalidValue = fieldError.getRejectedValue();
           String message = fieldError.getDefaultMessage();

           return String.format("%s.%s %s, but it was %s", className, property, message, invalidValue);
       }
       return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
   }
}
