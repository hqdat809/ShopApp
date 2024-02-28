package com.hqdat.ecommerce.exception.validation;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorResponse {
    private String message;
    private String status = HttpStatus.BAD_REQUEST.name();
    private Integer code = HttpStatus.BAD_REQUEST.value();
    private Date timestamp = new Date();

    public ValidationErrorResponse(String message) {
        this.message = message;
    }
}
