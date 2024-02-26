package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.UserDTO;
import com.hqdat.ecommerce.model.Role;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.RoleRepository;
import com.hqdat.ecommerce.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;

    public UserServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User convertDTO(UserDTO userDTO) {
        Role existingRole = roleRepository.findById(userDTO.getRoleID())
                .orElseThrow(() -> new RuntimeException("Role id not found"));

        return User
                .builder()
                .address(userDTO.getAddress())
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(existingRole)
                .build();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public User getUserByID(Long userID) {
        return null;
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return null;
    }

    @Override
    public User updateUser(Long userID, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long userID) {

    }
}
