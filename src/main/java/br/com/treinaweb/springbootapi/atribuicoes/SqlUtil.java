package br.com.treinaweb.springbootapi.atribuicoes;

import br.com.treinaweb.springbootapi.conexao.DatabaseConnection;

import java.lang.reflect.Field;
import java.sql.*;

public class SqlUtil {
    public static void executeInsert(String sql, Connection connection, Object object) throws SQLException {
        if (connection == null) {
            throw new SQLException("A conexão não foi inicializada corretamente.");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParameters(preparedStatement, object);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Erro ao executar a inserção no banco de dados", e);
        }
    }

    public static void setParameters(PreparedStatement preparedStatement, Object object) throws SQLException {
        Field[] fields = object.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                preparedStatement.setObject(i + 1, fields[i].get(object));
            } catch (IllegalAccessException e) {
                throw new SQLException("Erro ao acessar um campo da classe durante a reflexão", e);
            }
        }
    }
}
