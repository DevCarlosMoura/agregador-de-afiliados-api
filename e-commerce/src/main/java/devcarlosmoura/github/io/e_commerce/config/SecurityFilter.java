package devcarlosmoura.github.io.e_commerce.config;

import devcarlosmoura.github.io.e_commerce.repositories.UserRepository;
import devcarlosmoura.github.io.e_commerce.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        //Testes de DEBUG pois nao estava encontrando o problema do token vazio. O token estava chegando, mas a validação falhava e não dava acesso a nada.
        if (token != null) {
            var login = tokenService.validateToken(token);
            System.out.println("DEBUG - O que saiu da Fábrica de Tokens: '" + login + "'");
            if (login != null && !login.trim().isEmpty()) {
                UserDetails user = userRepository.findByLogin(login.trim());
                System.out.println(" DEBUG - O que o Banco achou: " + (user != null ? user.getUsername() : "NADA"));
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println(" DEBUG - Catraca liberada para o usuário!");
                } else {
                    System.out.println(" DEBUG - O e-mail estava no token, mas sumiu do banco de dados!");
                }

            } else {
                System.out.println(" DEBUG - A validação falhou. A string veio vazia.");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}