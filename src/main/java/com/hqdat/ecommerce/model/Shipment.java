package com.hqdat.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shipment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "zip_code", length = 100)
    private String zip_code;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @ManyToOne
    private User user;
}
