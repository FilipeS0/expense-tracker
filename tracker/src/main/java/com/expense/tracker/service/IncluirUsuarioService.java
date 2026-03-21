package com.filipe.socialmedia.service.usuario;


import com.filipe.socialmedia.controller.request.usuario.UsuarioRequest;
import com.filipe.socialmedia.controller.response.usuario.UsuarioResponse;
import com.filipe.socialmedia.domain.Usuario;
import com.filipe.socialmedia.mapper.UsuarioMapper;
import com.filipe.socialmedia.repository.UsuarioRepository;
import com.filipe.socialmedia.service.validator.ValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;


    public UsuarioResponse incluir(UsuarioRequest request) {
        validarUsuarioService.validar(request);

        usuarioRepository.findByEmail(request.getEmail())
                .ifPresent(u -> { throw new ResponseStatusException(CONFLICT, "E-mail já cadastrado"); });

        Usuario usuario = UsuarioMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }
}
