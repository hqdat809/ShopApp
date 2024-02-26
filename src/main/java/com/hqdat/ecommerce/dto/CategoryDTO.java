package com.hqdat.ecommerce.dto;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
}
