package br.com.treinaweb.springbootapi.conexao;

import java.sql.Connection;

public interface DatabaseConnection {
    Connection getConnection();
}
