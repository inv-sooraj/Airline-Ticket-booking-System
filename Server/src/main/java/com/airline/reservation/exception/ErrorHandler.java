package com.airline.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import aj.org.objectweb.asm.Handle;
@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler{
 @ExceptionHandler(HandledException.class)
 public ResponseEntity<ApplicationError>FieldsNotFoundException(HandledException e,WebRequest webrequest)   {
    ApplicationError error = new ApplicationError();
    error.setCode(1001);
    error.setMessage(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
}
