package com.airline.reservation.security.util;
public class TokenExpiredException extends IllegalArgumentException {
    public TokenExpiredException(String s) {
        super(s);
    }
    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
