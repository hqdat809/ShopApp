package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.auth.AuthenticationResponse;
import com.hqdat.ecommerce.model.Token;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.CategoryRepository;
import com.hqdat.ecommerce.repository.TokenRepository;
import com.hqdat.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TokenService(JwtService jwtService, TokenRepository tokenRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public AuthenticationResponse refreshToken(String refreshToken) {
        String userEmail = jwtService.extractUsername(refreshToken);
        Optional<Token> tokenFromDb = tokenRepository.findByToken(refreshToken);

        Optional<User> user = userRepository.findByEmail(userEmail);

        if(user.isPresent() && tokenFromDb.isPresent() && !tokenFromDb.get().isRevoked()) {
            //      REVOKE TOKEN
            Optional<List<Token>> oldTokens = tokenRepository.findByUser(user.get());
            oldTokens.ifPresent(tokens -> tokens.forEach(token -> {
                token.setRevoked(true);
                tokenRepository.save(token);
            }));

            AuthenticationResponse jwtTokens = jwtService.generateToken(user.get());

            Token accessToken = Token
                    .builder()
                    .token(jwtTokens.getAccessToken())
                    .tokenType("ACCESS_TOKEN")
                    .revoked(false)
                    .expired(false)
                    .user(user.get())
                    .build();

            Token refreToken = Token
                    .builder()
                    .token(jwtTokens.getRefreshToken())
                    .tokenType("REFRESH_TOKEN")
                    .revoked(false)
                    .expired(false)
                    .user(user.get())
                    .build();

            tokenRepository.save(accessToken);
            tokenRepository.save(refreToken);

            return jwtTokens;
        } else throw new RuntimeException("Token is not valid");
    }
}
