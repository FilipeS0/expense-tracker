package com.expense.tracker.service;


import com.expense.tracker.controller.request.UsuarioRequest;
import com.expense.tracker.controller.response.UsuarioResponse;
import com.expense.tracker.mapper.UsuarioMapper;
import com.expense.tracker.domain.Usuario;
import com.expense.tracker.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class IncluirUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public IncluirUsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse incluir(UsuarioRequest request) {

        usuarioRepository.findByEmail(request.getEmail())
                .ifPresent(u -> { throw new ResponseStatusException(CONFLICT, "E-mail já cadastrado"); });

        Usuario usuario = UsuarioMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }
}
