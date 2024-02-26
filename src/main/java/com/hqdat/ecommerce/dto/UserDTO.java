package com.hqdat.ecommerce.dto;

import com.hqdat.ecommerce.model.Order;
import com.hqdat.ecommerce.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String password;
    private Long roleID;
}
