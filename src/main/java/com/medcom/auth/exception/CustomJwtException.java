package com.medcom.auth.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.BadCredentialsException;

public class CustomJwtException extends RuntimeException {

    public CustomJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public static CustomJwtException getCustomJwtException(Exception exception) {
        CustomJwtException customJwtException = null;
        if (exception instanceof BadCredentialsException) {
            customJwtException = new CustomJwtException("The credentials provided are not valid.", exception);
        }
        if (exception instanceof SignatureException) {
            customJwtException = new CustomJwtException("The access token is invalid, unable to decode.", exception);
        }
        if (exception instanceof ExpiredJwtException) {
            customJwtException = new CustomJwtException("The informed access token is expired.", exception);
        }
        if (exception instanceof UnsupportedJwtException || exception instanceof MalformedJwtException) {
            customJwtException = new CustomJwtException("Unable to decode access token", exception);
        }
        return customJwtException;
    }

}
