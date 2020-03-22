package com.negotium.negotiumapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Product with this ID is already exist")
public class DuplicateProductIdException extends RuntimeException {
    public DuplicateProductIdException(String message) {
        super(message);
    }
}
