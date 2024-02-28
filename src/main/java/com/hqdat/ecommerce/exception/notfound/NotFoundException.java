package com.hqdat.ecommerce.exception.notfound;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
