package com.hqdat.ecommerce.exception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundHandlerException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundResponse> handleNotFoundException(NotFoundException ex) {
        NotFoundResponse errors = new NotFoundResponse();
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.name());
        errors.setCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
