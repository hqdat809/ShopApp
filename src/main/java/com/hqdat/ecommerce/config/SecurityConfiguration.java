package com.hqdat.ecommerce.config;

import com.hqdat.ecommerce.exception.auth.CustomBasicAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.lang.reflect.Method;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req

                        .requestMatchers(HttpMethod.POST,"/api/categories").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/categories").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/categories/*").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/products").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/products").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/products/*").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/orders").hasAnyAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/order-details").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/order-details").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/order-details").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/order-details/by-user/*").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/order-details/by-order/*").hasAnyAuthority("ROLE_ADMIN")

                        .requestMatchers("/api/auth/*").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .anyRequest()
                        .authenticated())
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.customBasicAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
