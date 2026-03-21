package com.expense.tracker.service;


import com.expense.tracker.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import static com.expense.tracker.mapper.UsuarioMapper.toResponse;

@Service
public class BuscarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    privte final UsuarioAuten

    public BuscarUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    public UsuarioResponse buscar() {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        return toResponse(usuarioAutenticado);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
