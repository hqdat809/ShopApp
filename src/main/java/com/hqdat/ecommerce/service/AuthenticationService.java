package com.hqdat.ecommerce.service;

import com.hqdat.ecommerce.auth.AuthenticationRequest;
import com.hqdat.ecommerce.auth.AuthenticationResponse;
import com.hqdat.ecommerce.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
