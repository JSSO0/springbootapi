package br.com.treinaweb.springbootapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Configuração de segurança do Spring
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Serviço personalizado para carregar informações do usuário
    private final UserDetailsService userDetailsService;

    // Construtor que recebe o serviço de detalhes do usuário como parâmetro
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Configuração do gerenciador de autenticação
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Configuração do codificador de senha (usando BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuração do gerenciador de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configura o gerenciador de autenticação com o serviço de detalhes do usuário e codificador de senha
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Configuração das regras de autorização e autenticação HTTP
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desativa proteção CSRF
            .authorizeRequests()
            .antMatchers("/auth/login**").permitAll() // Permite acesso irrestrito a rotas públicas
            .antMatchers("/api/pessoas/**").hasRole("ADMIN") // Exige ROLE_ADMIN para rotas de pessoa
            .anyRequest().authenticated() // Todas as outras solicitações requerem autenticação
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManagerBean())); // Adiciona filtro de autenticação JWT
    }
}