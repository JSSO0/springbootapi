package br.com.treinaweb.springbootapi.conexao;


import br.com.treinaweb.springbootapi.implement.PessoaDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/db_api");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Bean
    public Connection connection(DataSource dataSource) throws SQLException {
        // Retrieve a connection from the DataSource
        return dataSource.getConnection();
    }

    @Bean
    public PessoaDAO pessoaDAO(DataSource dataSource) throws SQLException {
        return new PessoaDAO(dataSource.getConnection());
    }
}