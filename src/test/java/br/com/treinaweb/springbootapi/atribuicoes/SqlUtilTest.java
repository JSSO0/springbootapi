package br.com.treinaweb.springbootapi.atribuicoes;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;


import static org.mockito.Mockito.*;


public class SqlUtilTest {

    @InjectMocks
    private SqlUtil sqlUtil;

    @Mock
    private Connection connectionMock;

    @Mock
    private PreparedStatement preparedStatementMock;

    public SqlUtilTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteInsert() throws SQLException {
        // Dados de exemplo
        String sql = "INSERT INTO tabela (coluna1, coluna2) VALUES (?, ?)";
        TestObject testObject = new TestObject();
        testObject.setColuna1("valor1");
        testObject.setColuna2("valor2");

        // Configuração do mock
        when(connectionMock.prepareStatement(sql)).thenReturn(preparedStatementMock);

        // Chamada do método
        assertDoesNotThrow(() -> SqlUtil.executeInsert(sql, connectionMock, testObject));

        // Verificações
        verify(connectionMock, times(1)).prepareStatement(sql);
        verify(preparedStatementMock, times(1)).setObject(1, "valor1");
        verify(preparedStatementMock, times(1)).setObject(2, "valor2");
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

    @Test
    public void testSetParameters() throws SQLException, NoSuchFieldException {
        // Dados de exemplo
        TestObject testObject = new TestObject();
        testObject.setColuna1("valor1");
        testObject.setColuna2("valor2");

        // Configuração do mock
        when(preparedStatementMock.getParameterMetaData()).thenReturn(null);

        // Chamada do método
        assertDoesNotThrow(() -> SqlUtil.setParameters(preparedStatementMock, testObject));

        // Verificações
        verify(preparedStatementMock, times(1)).setObject(1, "valor1");
        verify(preparedStatementMock, times(1)).setObject(2, "valor2");
    }

    // Classe de exemplo para os testes
    private static class TestObject {
        private String coluna1;
        private String coluna2;

        public String getColuna1() {
            return coluna1;
        }

        public void setColuna1(String coluna1) {
            this.coluna1 = coluna1;
        }

        public String getColuna2() {
            return coluna2;
        }

        public void setColuna2(String coluna2) {
            this.coluna2 = coluna2;
        }
    }
}
