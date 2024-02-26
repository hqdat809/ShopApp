package com.hqdat.ecommerce.repository;

import com.hqdat.ecommerce.model.Token;
import com.hqdat.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<List<Token>> findByUser(User user);

    Optional<Token> findByToken(String token);
}