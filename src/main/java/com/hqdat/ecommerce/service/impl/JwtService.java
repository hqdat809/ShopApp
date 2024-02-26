package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.auth.AuthenticationResponse;
import com.hqdat.ecommerce.exception.auth.TokenNotValidException;
import com.hqdat.ecommerce.model.Token;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {
    private final TokenRepository tokenRepository;

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token)  {
//        userName = subject of claims
        return extractClaim(token, Claims::getSubject);
    }

//    Check token is valid??
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        Optional<Token> tokenFromDb = tokenRepository.findByToken(token);

        if(tokenFromDb.isPresent()) {
            return userName.equals(userDetails.getUsername()) && !isTokenExpired(token) && !tokenFromDb.get().isRevoked();
        }
        return false;
    }

//    Check token is expired??
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

//    Extract expiration from the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

//    Extract the claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws TokenNotValidException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public AuthenticationResponse generateToken(User userDetails) {
        return buildToken(new HashMap<>(), userDetails);
    }

    public AuthenticationResponse buildToken(
            Map<String, Object> extraClaims,
            User userDetails
    ) {
        Map<String,Object> info = new HashMap<>();
        info.put("email", userDetails.getUsername());
        info.put("role", userDetails.getRole().getName());
        info.put("phone", userDetails.getPhoneNumber());
        info.put("fullName", userDetails.getFullName());

        String accessToken = Jwts
                .builder()
                .setClaims(info)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts
                .builder()
                .setClaims(info)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 96))
                .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                .compact();

        AuthenticationResponse authResponse = new AuthenticationResponse();

        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);

        return authResponse;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    Create SignInKey from SECRET_KEY
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
