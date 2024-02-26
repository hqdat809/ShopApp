package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.auth.AuthenticationRequest;
import com.hqdat.ecommerce.auth.AuthenticationResponse;
import com.hqdat.ecommerce.auth.RegisterRequest;
import com.hqdat.ecommerce.model.Role;
import com.hqdat.ecommerce.model.Token;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.RoleRepository;
import com.hqdat.ecommerce.repository.TokenRepository;
import com.hqdat.ecommerce.repository.UserRepository;
import com.hqdat.ecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");

        if (userRole.isPresent()) {
            User user = User.builder()
                    .email(request.getEmail())
                    .address(request.getAddress())
                    .fullName(request.getFullName())
                    .phoneNumber(request.getPhoneNumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(userRole.get())
                    .build();

            userRepository.save(user);
            return jwtService.generateToken(user);
        }

        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        AuthenticationResponse jwtTokens = jwtService.generateToken(user);

//      REVOKE TOKEN
        Optional<List<Token>> oldTokens = tokenRepository.findByUser(user);

        oldTokens.ifPresent(tokens -> tokens.forEach(token -> {
            token.setRevoked(true);
            tokenRepository.save(token);
        }));

        Token accessToken = Token
                .builder()
                .token(jwtTokens.getAccessToken())
                .tokenType("ACCESS_TOKEN")
                .revoked(false)
                .expired(false)
                .user(user)
                .build();

        Token refreshToken = Token
                .builder()
                .token(jwtTokens.getRefreshToken())
                .tokenType("REFRESH_TOKEN")
                .revoked(false)
                .expired(false)
                .user(user)
                .build();

        tokenRepository.save(accessToken);
        tokenRepository.save(refreshToken);

        return jwtTokens;
    }
}
