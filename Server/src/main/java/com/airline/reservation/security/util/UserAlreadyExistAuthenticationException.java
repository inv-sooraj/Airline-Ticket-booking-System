package com.airline.reservation.security.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {
    
    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}