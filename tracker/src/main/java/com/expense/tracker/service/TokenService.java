package com.expense.tracker.service;

import com.expense.tracker.controller.response.LoginResponse;
import com.expense.tracker.domain.AppUser;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public LoginResponse generateToken(AppUser user) {
        long expiresIn = 600L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("seguranca-api")
                .subject(user.getName())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", user.getEmail())
                .build();

        String token = jwtEncoder
                .encode(JwtEncoderParameters.from(jwt))
                .getTokenValue();

        return new LoginResponse(token, expiresIn);
    }
}
