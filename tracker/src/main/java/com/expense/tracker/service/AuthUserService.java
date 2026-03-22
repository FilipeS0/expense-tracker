package com.expense.tracker.service;


import com.expense.tracker.domain.AppUser;
import com.expense.tracker.repository.AppUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class AuthUserService {

    private final AppUserRepository userRepository;

    public AuthUserService(AppUserRepository usuarioRepository) {
        this.userRepository = usuarioRepository;
    }

    public String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return jwt.getClaim("email");
    }

    public AppUser get() {
        return userRepository.findByEmail(getEmail())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Usuário não existe ou não está autenticado"));
    }
}
