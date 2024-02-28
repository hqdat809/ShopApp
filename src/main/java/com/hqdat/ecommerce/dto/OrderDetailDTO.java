package com.hqdat.ecommerce.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {
    private Long orderID;
    private Long productID;
    private Integer quantity;
    private String color;
}
