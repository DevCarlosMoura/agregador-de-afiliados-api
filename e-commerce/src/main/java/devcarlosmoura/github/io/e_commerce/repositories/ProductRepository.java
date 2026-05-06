package devcarlosmoura.github.io.e_commerce.repositories;

import devcarlosmoura.github.io.e_commerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
