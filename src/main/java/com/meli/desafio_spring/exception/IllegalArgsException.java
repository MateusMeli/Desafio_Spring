package com.meli.desafio_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgsException extends IllegalArgumentException {
    public IllegalArgsException(String message) {
        super(message);
    }
}
