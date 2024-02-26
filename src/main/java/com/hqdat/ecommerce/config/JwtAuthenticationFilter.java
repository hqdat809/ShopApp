package com.hqdat.ecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hqdat.ecommerce.exception.auth.TokenNotValidException;
import com.hqdat.ecommerce.repository.TokenRepository;
import com.hqdat.ecommerce.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final UserDetailsService userDetailsService;
//    doFilterInternal được sử dụng để thực hiện các hoạt động lọc trên các yêu cầu
//    HTTP và phản hồi của ứng dụng Spring Boot
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

//        Authorization: Bearer token
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        String userEmail = "";

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            filterChain.doFilter(request, response);
            return;
        } else {
            jwt = authHeader.substring(7);
        }
        try {
//          Extract the useEmail from JWT token
            userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//              Check token is Valid??
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
//              Set context is authenticated
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    throw new RuntimeException("Token is not valid");
                }
            }
        } catch (Exception ex) {
//          Người dùng đã được xác thực nhưng không có quyền
            response.setHeader("error", ex.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", ex.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        filterChain.doFilter(request, response);
    }
}
