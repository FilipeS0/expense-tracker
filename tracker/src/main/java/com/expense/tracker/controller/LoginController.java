package com.expense.tracker.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final BuscarUsuarioService buscarUsuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginController(BuscarUsuarioService buscarUsuarioService, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.buscarUsuarioService = buscarUsuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> optUser = buscarUsuarioService.buscarPorEmail(loginRequest.getEmail());

        if (optUser.isEmpty() || !isLoginCorreto(loginRequest.getSenha(), optUser.get().getSenha())) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        }

        Usuario usuario = optUser.get();

        long expiresIn = 600L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("seguranca-api")
                .subject(usuario.getNomeCompleto())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", usuario.getEmail())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(token, expiresIn));
    }

    private boolean isLoginCorreto(String password, String savedPassowrd) {
        return passwordEncoder.matches(password, savedPassowrd);
    }
}
