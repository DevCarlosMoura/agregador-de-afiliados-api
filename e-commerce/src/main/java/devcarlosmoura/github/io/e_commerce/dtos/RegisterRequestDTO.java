package devcarlosmoura.github.io.e_commerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record RegisterRequestDTO(@NotBlank(message = "O e-mail é obrigatório")
                                 @Email(message = "Formato de E-mail inválido") String login,

                                 @NotBlank(message = "A senha é obrigatória")
                                 @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres") String password,

                                @NotBlank(message = "O campo de confirmação de senha é obrigatório")
                                 String passwordConfirm
                                 ){
}
