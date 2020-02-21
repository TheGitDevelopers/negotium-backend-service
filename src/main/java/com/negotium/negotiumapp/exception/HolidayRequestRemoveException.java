package com.negotium.negotiumapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The request could not be deleted. Please try again later")
public class HolidayRequestRemoveException extends RuntimeException {
}
