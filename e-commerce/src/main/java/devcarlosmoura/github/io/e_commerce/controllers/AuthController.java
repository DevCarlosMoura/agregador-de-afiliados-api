package devcarlosmoura.github.io.e_commerce.controllers;

import devcarlosmoura.github.io.e_commerce.dtos.LoginRequestDTO;
import devcarlosmoura.github.io.e_commerce.dtos.LoginResponseDTO;
import devcarlosmoura.github.io.e_commerce.dtos.RegisterRequestDTO;
import devcarlosmoura.github.io.e_commerce.entities.User;
import devcarlosmoura.github.io.e_commerce.services.AuthService;
import devcarlosmoura.github.io.e_commerce.services.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
       var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
       var auth = authenticationManager.authenticate(usernamePassword);
       var token = tokenService.generateToken((User) Objects.requireNonNull(auth.getPrincipal()));
       return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
