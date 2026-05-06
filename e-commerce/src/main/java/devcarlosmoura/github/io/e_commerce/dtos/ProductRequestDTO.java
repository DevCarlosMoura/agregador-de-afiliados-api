package devcarlosmoura.github.io.e_commerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(message = "O título do produto é obrigatório")
        String title,

        @NotBlank(message = "A descrição do produto é obrigatória")
        String description,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal price,

        @URL(message = "A URL da imagem deve ser um link válido")
        String imageUrl,

        @NotBlank(message = "O link de afiliado é obrigatório")
        @URL(message = "O link de afiliado deve ser um link válido")
        String affiliateLink
) {
}