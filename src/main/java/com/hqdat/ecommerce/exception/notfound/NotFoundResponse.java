package com.hqdat.ecommerce.exception.notfound;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotFoundResponse {
    private String message;
    private String status = HttpStatus.NOT_FOUND.name();
    private Integer code = HttpStatus.NOT_FOUND.value();
    private Date timestamp = new Date();
}
