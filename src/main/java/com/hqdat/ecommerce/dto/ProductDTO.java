package com.hqdat.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;

    private Float price;

    private String description;

    private String thumbnail;

    private Long categoryID;
}
