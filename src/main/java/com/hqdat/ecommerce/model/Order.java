package com.hqdat.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "note", length = 100)
    private String note;

    @Column(name = "order_date")
    private Date createAt;

    @Column(name = "shipping_method", length = 100)
    private String shippingMethod;

    @Column(name = "payment_method", length = 100)
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private boolean active;

    @Column(name = "updated_at")
    private Date updatedAt;

    private Float totalMoney;

    private Date shippingDate;

    @PrePersist
    protected void onCreate() {
        createAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
};
