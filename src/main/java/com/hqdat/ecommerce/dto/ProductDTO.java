package com.hqdat.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name is require")
    private String name;

    @NotNull(message = "Price of product not null")
    private Float price;

    private String description = "";

    private String thumbnail;

    @NotNull(message = "Category can't be null")
    private Long categoryID;
}
