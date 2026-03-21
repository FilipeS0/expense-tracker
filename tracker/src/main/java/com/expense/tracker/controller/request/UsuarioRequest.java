package com.expense.tracker.controller.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UsuarioRequest {

    @NotBlank
    @Size(min = 3, max = 255)
    private String nomeCompleto;

    @NotNull @Email
    private String email;

    @NotBlank
    private String senha;

    @Size(max = 50)
    private String apelido;

    @NotNull
    private LocalDate dataNascimento;

    @Nullable
    private String imagemPerfil;
}
