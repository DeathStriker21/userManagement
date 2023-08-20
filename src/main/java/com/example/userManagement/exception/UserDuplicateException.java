package com.example.userManagement.exception;

import org.springframework.security.core.AuthenticationException;

public class UserDuplicateException extends RuntimeException{

    public UserDuplicateException(String message) {
        super(message);
    }

}
