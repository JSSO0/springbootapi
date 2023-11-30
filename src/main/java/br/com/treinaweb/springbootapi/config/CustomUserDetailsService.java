package br.com.treinaweb.springbootapi.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Simulação de um usuário com papel ADMIN
    private static final UserDetails ADMIN_USER = User.builder()
        .username("admin")
        .password("{bcrypt}$2a$10$yXq5LyTnlO.6/My8sNeM/e.WIcU5kZbq2hKx2MnUdbIy4TCD6Hvdq") // Senha: password
        .roles("ADMIN")
        .build();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return ADMIN_USER;
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}