package com.negotium.negotiumapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Nazwa użytkownika zajęta")
public class DuplicateUsernameException {
}
