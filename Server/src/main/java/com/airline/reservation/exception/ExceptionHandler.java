package com.airline.reservation.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.airline.reservation.json.ResBody;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
        Map<String, Object> objectBody = new HashMap<>();
        ResBody body = new ResBody();
        objectBody.put("Status", httpStatus.value());

        // Get all errors
        List<String> exceptionalErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
                // System.out.println(exceptionalErrors);

        List<ApplicationError> err = new ArrayList<>();
    
        for(String s : exceptionalErrors)
        {
            System.out.println("s="+s);
            if((s.equals("1001"))){
                body.getErrors().add(new ApplicationError("1001","Invalid Fullname"));
                  }
                  if((s.equals("1002"))){
                    body.getErrors().add(new ApplicationError("1002","Invalid Email"));
                      }
                      if((s.equals("1004"))){
                        body.getErrors().add(new ApplicationError("1004","Invalid Phone Number"));
                          }
            //body.getErrors().add(new ApplicationError("1002","Invalid email"));
          
            //
            // body.getErrors().add(new ApplicationError("1004","Invalid phone"));
          
        }

        objectBody.put("Errors", body);

        return new ResponseEntity<>(body, httpStatus);
    }
}