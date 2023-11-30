package br.com.treinaweb.springbootapi.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
// Classe que estende UsernamePasswordAuthenticationFilter para processar autenticação JWT
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // Gerenciador de autenticação fornecido como dependência
    private final AuthenticationManager authenticationManager;

    // Provedor de tokens JWT utilizado para gerar e validar tokens
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    // Construtor que recebe um AuthenticationManager como parâmetro
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        // Inicializa o gerenciador de autenticação
        this.authenticationManager = authenticationManager;
        // Cria uma instância do provedor de tokens JWT
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Sobrescreve o método de tentativa de autenticação
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Extrai o token do cabeçalho da solicitação
            String token = request.getHeader("Authorization");

            // Verifica se o token existe e começa com "Bearer "
            if (token != null && token.startsWith("Bearer ")) {
                // Remove o prefixo "Bearer " para obter o token real
                token = token.substring(7);
            }

            // Valida o token usando o provedor de tokens JWT
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // Obtém o nome de usuário do token
                String username = jwtTokenProvider.getUsernameFromToken(token);

                // Se o nome de usuário for obtido, cria um token de autenticação
                if (username != null) {
                    // Criação de token de autenticação
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, null);
                    // Autentica usando o gerenciador de autenticação
                    return authenticationManager.authenticate(auth);
                }
            }
        } catch (Exception e) {
            // Lidar com exceções, se necessário (aqui apenas imprime a exceção)
            e.printStackTrace();
        }

        return null; // Retorna null se a autenticação falhar
    }

    // Sobrescreve o método de autenticação bem-sucedida
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Se a autenticação for bem-sucedida, gera o token e adiciona ao cabeçalho da resposta
        String token = jwtTokenProvider.generateToken(authResult);
        response.addHeader("Authorization", "Bearer " + token);
    }
}