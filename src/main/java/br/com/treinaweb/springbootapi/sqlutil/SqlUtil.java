package br.com.treinaweb.springbootapi.sqlutil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SqlUtil {
    public static void executeInsert(String sql, Connection connection, Object object) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setParameters(preparedStatement, object);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            // Trate exceções adequadamente
        }
    }

    public static void setParameters(PreparedStatement preparedStatement, Object object) throws SQLException {
        Field[] fields = object.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                preparedStatement.setObject(i + 1, fields[i].get(object));
            } catch (IllegalAccessException e) {
                // Trate exceções adequadamente
            }
        }
    }
}
