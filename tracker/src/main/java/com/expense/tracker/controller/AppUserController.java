package com.expense.tracker.controller;

import com.expense.tracker.controller.request.UsuarioRequest;
import com.expense.tracker.controller.response.UsuarioResponse;
import com.expense.tracker.service.BuscarUsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final BuscarUsuarioService buscarUsuarioService;
    private final Incluir

    @PostMapping
    public UsuarioResponse incluir(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @GetMapping("/me")
    public UsuarioResponse buscar() {
        return buscarUsuarioService.buscar();
    }

}
