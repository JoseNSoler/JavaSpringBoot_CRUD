package com.sofka.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class exceptionSubError extends exceptionAbstSubErr{

   private String object;
   private String field;
   private Object rejectedValue;
   private String message;

   exceptionSubError(String object, String message) {
       this.object = object;
       this.message = message;
   }
}
