package com.hqdat.ecommerce.controller;

import com.hqdat.ecommerce.auth.AuthenticationRequest;
import com.hqdat.ecommerce.auth.AuthenticationResponse;
import com.hqdat.ecommerce.auth.RefreshTokenRequest;
import com.hqdat.ecommerce.auth.RegisterRequest;
import com.hqdat.ecommerce.service.AuthenticationService;
import com.hqdat.ecommerce.service.impl.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            @RequestBody RefreshTokenRequest refreshToken
    ) {
        return ResponseEntity.ok(tokenService.refreshToken(refreshToken.getRefreshToken()));
    }
}
