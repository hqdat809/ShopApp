package com.hqdat.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider", length = 20)
    private String provider;

    @Column(name = "provider_id", length = 50)
    private String providerId;

    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
