package devcarlosmoura.github.io.e_commerce.services;

import devcarlosmoura.github.io.e_commerce.dtos.RegisterRequestDTO;
import devcarlosmoura.github.io.e_commerce.entities.User;
import devcarlosmoura.github.io.e_commerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestDTO request) {
        if (!request.password().equals(request.passwordConfirm())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As senhas não são iguais!");
        }

        if (userRepository.findByLogin(request.login()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este E-mail já esta em uso!");
        }

        String encryptedPassword = passwordEncoder.encode(request.password());

        User newUser = new User();
        newUser.setLogin(request.login());
        newUser.setPassword(encryptedPassword);

        newUser.setRole("USER");

        userRepository.save(newUser);
    }
}
