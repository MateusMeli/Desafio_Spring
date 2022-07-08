package com.meli.desafio_spring.handler;

import com.meli.desafio_spring.exception.FileNotFoundException;
import com.meli.desafio_spring.exception.IllegalArgsException;
import com.meli.desafio_spring.exception.NotFoundException;
import com.meli.desafio_spring.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotFoundExeHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(NotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Data object not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(FileNotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Data object not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(IllegalArgsException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(IllegalArgsException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request.")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}