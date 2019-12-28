package com.negotium.negotiumapp.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException() {
        super("login failed");
    }
}
