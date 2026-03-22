package com.expense.tracker.service;


import com.expense.tracker.controller.response.UserResponse;
import com.expense.tracker.domain.AppUser;
import com.expense.tracker.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.expense.tracker.mapper.UserMapper.toResponse;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {

    private final AppUserRepository userRepository;
    private final AuthUserService authUserService;


    public UserService(AppUserRepository usuarioRepository, AuthUserService usuarioAutenticadoService) {
        this.userRepository = usuarioRepository;
        this.authUserService = usuarioAutenticadoService;
    }

    public UserResponse getUser() {
        AppUser authedUser = authUserService.get();
        return toResponse(authedUser);
    }

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserResponse getUserById(Long id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario nao encontrado"));

        return toResponse(user);
    }
}
