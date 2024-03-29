package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.UserDTO;
import com.hqdat.ecommerce.exception.notfound.NotFoundException;
import com.hqdat.ecommerce.model.Role;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.RoleRepository;
import com.hqdat.ecommerce.repository.UserRepository;
import com.hqdat.ecommerce.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public User convertDTO(UserDTO userDTO) {
        Role existingRole = roleRepository.findById(userDTO.getRoleID())
                .orElseThrow(() -> new NotFoundException("Role id not found"));

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
        return userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundException("User not found with id user: " + userID));
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
