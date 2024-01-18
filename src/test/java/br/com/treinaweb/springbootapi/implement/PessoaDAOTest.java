package br.com.treinaweb.springbootapi.implement;
/*
import br.com.treinaweb.springbootapi.atribuicoes.Definicoes;
import br.com.treinaweb.springbootapi.atribuicoes.SqlUtil;
import br.com.treinaweb.springbootapi.atribuicoes.SqlUtil.*;
import br.com.treinaweb.springbootapi.entity.Pessoa;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SqlUtil.class)

public class PessoaDAOTest {
    private PessoaDAO pessoaDAO;
    private Connection mockConnection;
    private Definicoes mockPessoaMapper;

    @Before
    public void setUp() {
        mockConnection = mock(Connection.class);
        mockPessoaMapper = mock(Definicoes.class);
        try {
            pessoaDAO = new PessoaDAO(mockConnection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCriarPessoa() throws SQLException {
        Pessoa mockPessoa = mock(Pessoa.class);

        try {
            pessoaDAO.criarPessoa(mockPessoa);

            // Verifica se o método executeInsert foi chamado com os parâmetros corretos
            PowerMockito.verifyStatic(SqlUtil.class, times(1));
            SqlUtil.executeInsert(anyString(), eq(mockConnection), eq(mockPessoa));
        } catch (SQLException e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }
}*/