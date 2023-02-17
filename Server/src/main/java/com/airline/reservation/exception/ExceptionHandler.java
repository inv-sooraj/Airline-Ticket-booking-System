package com.airline.reservation.exception;
import java.util.ArrayList;
import java.util.HashMap;
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
                          if((s.equals("1010"))){
                            body.getErrors().add(new ApplicationError("1010","Airplane name is null"));
                            }
                          if((s.equals("1011"))){
                            body.getErrors().add(new ApplicationError("1011","Airplane name should be between 5 and 30"));
                            }
                          if((s.equals("1012"))){
                            body.getErrors().add(new ApplicationError("1012","Model number is null"));
                            }
                          if((s.equals("1013"))){
                            body.getErrors().add(new ApplicationError("1013","Model number should be between 5 and 30"));
                            }
                          if((s.equals("1014"))){
                            body.getErrors().add(new ApplicationError("1014","Total seat field is null"));
                            }
                          if((s.equals("1003"))){
                            body.getErrors().add(new ApplicationError("1003","email is null"));
                            }
                          if((s.equals("1005"))){
                            body.getErrors().add(new ApplicationError("1005","Passport number should be 8 charactors"));
                            }
                          if((s.equals("1006"))){
                            body.getErrors().add(new ApplicationError("1006","City max size 18"));
                            }
                           if((s.equals("1007"))){
                            body.getErrors().add(new ApplicationError("1007","country max size 18"));
                            }
                        }
                      
        objectBody.put("Errors", body);
        return new ResponseEntity<>(body, httpStatus);
    }
}