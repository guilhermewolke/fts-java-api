package com.github.guilhermewoelke.fts.api.infra.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class Exceptions {
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity erro400() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity erro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity erro500(HttpServerErrorException.InternalServerError ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity erro(Exception ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
