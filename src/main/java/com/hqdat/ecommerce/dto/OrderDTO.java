package com.hqdat.ecommerce.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderDTO {
    private Long userID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private Float totalMoney;
    private String shippingMethod;
    private String shippingAddress;
    private Date shippingDate;
    private String trackingNumber;
    private String paymentMethod;
    private boolean active;
}
