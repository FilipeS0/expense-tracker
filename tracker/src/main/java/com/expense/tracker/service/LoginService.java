package com.expense.tracker.service;

import com.expense.tracker.controller.request.LoginRequest;
import com.expense.tracker.controller.response.LoginResponse;
import com.expense.tracker.domain.AppUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserService getUserService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginService(PasswordEncoder passwordEncoder, UserService getUserService, TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.getUserService = getUserService;
        this.tokenService = tokenService;
    }

    public LoginResponse login(LoginRequest loginRequest) {

        AppUser user = getUserService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Usuário ou senha incorretos!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        }

        return tokenService.generateToken(user);
    }
}
