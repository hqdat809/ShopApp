package com.hqdat.ecommerce.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenNotValidException extends RuntimeException {
    private String message;

    public TokenNotValidException(String msg) {
        super(msg);
        this.message = msg;
    }
}
