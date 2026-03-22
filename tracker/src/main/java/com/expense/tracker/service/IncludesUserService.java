package com.expense.tracker.service;


import com.expense.tracker.controller.request.UserRequest;
import com.expense.tracker.controller.response.UserResponse;
import com.expense.tracker.mapper.UserMapper;
import com.expense.tracker.domain.AppUser;
import com.expense.tracker.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class IncludesUserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public IncludesUserService(AppUserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse includes(UserRequest request) {

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> { throw new ResponseStatusException(CONFLICT, "E-mail já cadastrado"); });

        AppUser appUser = UserMapper.toEntity(request);
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(appUser);

        return UserMapper.toResponse(appUser);
    }
}
