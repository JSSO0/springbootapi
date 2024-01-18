package br.com.treinaweb.springbootapi.atribuicoes;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtilTest {
    @Test
    public void testExecuteInsert() throws SQLException {
        String sql = "INSERT INTO pessoa (id, cpf, email, nome, telefone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        Pessoa mockPessoa = new Pessoa();
        mockPessoa.setId("aaaaaaaaaaaa");
        mockPessoa.setCpf("aaaaaaaaaaaa");
        mockPessoa.setEmail("aaaaaaaaaaaa");
        mockPessoa.setNome("aaaaaaaaaaaa");
        mockPessoa.setTelefone("aaaaaaaaaaaa");
        mockPessoa.setUsername("aaaaaaaaaaaa");
        mockPessoa.setPassword("aaaaaaaaaaaa");

        try {
            when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

            SqlUtil.executeInsert(sql, mockConnection, mockPessoa);

            // Verifica se os métodos foram chamados com os parâmetros corretos
            verify(mockPreparedStatement, times(1)).setObject(eq(1), eq("aaaaaaaaaaaa"));
            verify(mockPreparedStatement, times(1)).setObject(eq(2), eq("aaaaaaaaaaaa"));
            verify(mockPreparedStatement, times(1)).setObject(eq(3), eq("aaaaaaaaaaaa"));
            verify(mockPreparedStatement, times(1)).setObject(eq(4), eq("aaaaaaaaaaaa"));
            verify(mockPreparedStatement, times(1)).setObject(eq(5), eq("aaaaaaaaaaaa"));
            verify(mockPreparedStatement, times(1)).setObject(eq(6), eq("aaaaaaaaaaaa"));
            verify(mockPreparedStatement, times(1)).setObject(eq(7), eq("aaaaaaaaaaaa"));
            verify(mockPreparedStatement, times(1)).executeUpdate();
        } catch (SQLException e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }
}