package com.hqdat.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Float price;

    @Column(name = "number_products", length = 11)
    private Integer quantity;

    private Float totalMoney;

    private String color;

    @PrePersist
    protected void onCreate() {
        totalMoney = price * quantity;
    }

    @PreUpdate
    protected void onUpdate() {
        totalMoney = price * quantity;
    }
}
