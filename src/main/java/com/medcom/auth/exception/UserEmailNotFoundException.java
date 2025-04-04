package com.medcom.auth.exception;

public class UserEmailNotFoundException extends RuntimeException {

    public UserEmailNotFoundException(String email) {
        super("User with email %s not found".formatted(email));
    }

}
