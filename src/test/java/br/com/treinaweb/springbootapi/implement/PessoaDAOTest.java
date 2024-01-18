package br.com.treinaweb.springbootapi.implement;

import br.com.treinaweb.springbootapi.atribuicoes.SqlUtil;
import br.com.treinaweb.springbootapi.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class PessoaDAOTest {

    @Mock
    private SqlUtil sqlUtilMock;

    @Mock
    private Connection connectionMock;

    @Mock
    private PreparedStatement preparedStatementMock;

    private PessoaDAO pessoaDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        try {
            pessoaDAO = new PessoaDAO(sqlUtilMock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCriarPessoa() throws SQLException {
        // Criação de uma pessoa fictícia para o teste
        Pessoa pessoaParaTeste = new Pessoa();
        pessoaParaTeste.setId("1");
        pessoaParaTeste.setNome("João");
        pessoaParaTeste.setTelefone("123456789");
        pessoaParaTeste.setEmail("joao@example.com");
        pessoaParaTeste.setCpf("12345678901");
        pessoaParaTeste.setUsername("joao123");
        pessoaParaTeste.setPassword("senha123");

        // Configurar o comportamento esperado do mock
        doNothing().when(sqlUtilMock).executeInsert(any(String.class), any(Connection.class), eq(pessoaParaTeste));

        // Configurar comportamento esperado para conexão mock
        when(connectionMock.prepareStatement(any(String.class))).thenReturn(preparedStatementMock);

        // Chamar o método a ser testado
        try {
            pessoaDAO.criarPessoa(pessoaParaTeste);

            // Verificar se o método do mock foi chamado corretamente
            verify(sqlUtilMock, times(1)).executeInsert(any(String.class), eq(connectionMock), eq(pessoaParaTeste));

        } catch (SQLException e) {
            fail("Não deveria ter lançado exceção SQLException: " + e.getMessage());
        }
    }
}

