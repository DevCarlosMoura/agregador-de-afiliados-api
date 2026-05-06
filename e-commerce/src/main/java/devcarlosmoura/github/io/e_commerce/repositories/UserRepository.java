package devcarlosmoura.github.io.e_commerce.repositories;

import devcarlosmoura.github.io.e_commerce.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByLogin(String login);

}