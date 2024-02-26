package com.hqdat.ecommerce.service;

import com.hqdat.ecommerce.dto.UserDTO;
import com.hqdat.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(UserDTO userDTO);

    User getUserByID(Long userID);

    Page<User> getUsers(Pageable pageable);

    User updateUser(Long userID, UserDTO userDTO);

    void deleteUser(Long userID);
}
