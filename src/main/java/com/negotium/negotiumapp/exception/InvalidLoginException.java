package com.negotium.negotiumapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Login failed")
public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException() {
        super("login failed");
    }
}