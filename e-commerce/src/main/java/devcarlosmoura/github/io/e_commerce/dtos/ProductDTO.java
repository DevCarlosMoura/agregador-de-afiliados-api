package devcarlosmoura.github.io.e_commerce.dtos;

import devcarlosmoura.github.io.e_commerce.entities.Product;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(UUID id,
                         String title,
                         String description,
                         BigDecimal price,
                         String imageUrl,
                         String affiliateLink) {

    public ProductDTO(Product entity){
        this( entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImageUrl(),
                entity.getAffiliateLink() );
    }
}
