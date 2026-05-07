package devcarlosmoura.github.io.e_commerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank(message = "O e-mail é obrigatório")
                               @Email(message = "Formato de e-mail inválido")
                               String login,

                              @NotBlank(message = "A senha é obrigatória")
                               String password
    ) {
}
