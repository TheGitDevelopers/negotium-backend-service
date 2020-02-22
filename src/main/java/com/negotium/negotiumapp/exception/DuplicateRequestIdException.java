package com.negotium.negotiumapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Request with this ID is already exist")
public class DuplicateRequestIdException extends RuntimeException {
    public DuplicateRequestIdException(String message) {
        super(message);
    }
}
