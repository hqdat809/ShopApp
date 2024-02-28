package com.hqdat.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderDTO {
    @NotNull(message = "UserID can't be null")
    private Long userID;

    private String fullName;

    private String note;

    private Float totalMoney;

    @NotNull(message = "Shipping method can't be null")
    private String shippingMethod;

    @NotNull(message = "Shipping date can't be null")
    private Date shippingDate;

    @NotNull(message = "Payment method can't be null")
    private String paymentMethod;

    @NotNull(message = "ShipmentID can't be null")
    private Long shipmentID;

    @NotNull(message = "PaymentID date can't be null")
    private Long paymentID;

    private boolean active;
}
