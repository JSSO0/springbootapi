package br.com.treinaweb.springbootapi.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// Indica que esta classe é um serviço, geralmente usado para lógica de negócios relacionada à segurança
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Simulação de um usuário com papel ADMIN
    // Declaração de uma constante para representar um usuário administrador
    private static final UserDetails ADMIN_USER = User.builder()
        .username("admin")           // Nome de usuário é "admin"
        .password("{bcrypt}$2a$10$yXq5LyTnlO.6/My8sNeM/e.WIcU5kZbq2hKx2MnUdbIy4TCD6Hvdq") // Senha: password
        .roles("ADMIN")              // Papel/role do usuário é "ADMIN"
        .build();                    // Constrói o objeto UserDetails usando as configurações acima

    // Implementação do método da interface UserDetailsService para carregar UserDetails por nome de usuário
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Verifica se o nome de usuário fornecido é "admin"
        if ("admin".equals(username)) {
            // Retorna o usuário administrador simulado se o nome de usuário for "admin"
            return ADMIN_USER;
        }
        // Lança uma exceção se o nome de usuário não for encontrado
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}