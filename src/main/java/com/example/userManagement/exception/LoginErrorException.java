package com.example.userManagement.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginErrorException extends RuntimeException {

    public LoginErrorException(String message, AuthenticationException cause) {
        super(message, cause);
    }
}
