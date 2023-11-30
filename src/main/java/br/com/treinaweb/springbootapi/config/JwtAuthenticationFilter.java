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

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private static final String jwtSecret = "^_mi6c||[1_$4if461-7;w3&crdu6}#2$_>!{:k7wcw;,s2q1%4=?h`%`ne,_{i"; // Substitua pela sua chave secreta JWT
    private static final long jwtExpirationMs = 86400000; // 1 dia (ajuste conforme necessário)

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public static String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Extrair o token do cabeçalho da solicitação
            String token = request.getHeader("Authorization");

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7); // Remove o prefixo "Bearer "
            }

            // Validar o token
            if (token != null) {
                try {
                    Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                    String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();

                    if (username != null) {
                        // Criação de token de autenticação
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, null);
                        return authenticationManager.authenticate(auth);
                    }
                } catch (ExpiredJwtException e) {
                    // Token expirado, lidar com isso conforme necessário
                }
            }
        } catch (Exception e) {
            // Lidar com exceções, se necessário
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Se a autenticação for bem-sucedida, gera o token e adiciona ao cabeçalho da resposta
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        response.addHeader("Authorization", "Bearer " + token);
    }
}