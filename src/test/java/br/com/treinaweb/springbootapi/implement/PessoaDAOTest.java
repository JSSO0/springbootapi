package br.com.treinaweb.springbootapi.implement;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import br.com.treinaweb.springbootapi.atribuicoes.SqlUtil;
public class PessoaDAOTest { @InjectMocks
private PessoaDAO pessoaDAO;

    @Mock
    private Connection connectionMock;

    @Mock
    private PreparedStatement preparedStatementMock;

    @Mock
    private ResultSet resultSetMock;

    @Mock
    private SqlUtil sqlUtilMock;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        pessoaDAO = new PessoaDAO(connectionMock);
    }

    public void PessoaDAO(SqlUtil sqlUtil) {
        this.sqlUtilMock = sqlUtil;
    }
    /*
    @Test
    void testListarTodasAsPessoas() throws SQLException {
        // Configuração do mock
        String sql = "SELECT * FROM pessoa";
        when(connectionMock.prepareStatement(sql)).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

        // Chamada do método
        List<Pessoa> result = pessoaDAO.listarTodasAsPessoas();

        // Verificações
        verify(connectionMock, times(1)).prepareStatement(sql);
        verify(preparedStatementMock, times(1)).executeQuery();
        verify(resultSetMock, times(0)).next();  // ajuste conforme necessário
        // Adicione verificações específicas se necessário
    }*/
/* 
@Test
void testCriarPessoa() throws SQLException {
    // Dados de exemplo
    Pessoa pessoa = new Pessoa();
    pessoa.setCpf("12345678901");
    pessoa.setEmail("exemplo@teste.com");
    pessoa.setNome("Exemplo");
    pessoa.setTelefone("123456789");
    pessoa.setUsername("usuario");
    pessoa.setPassword("senha");

   
    SqlUtil sqlUtilMock = mock(SqlUtil.class);
    PessoaDAO pessoaDAO = new PessoaDAO(connectionMock);

    
    assertDoesNotThrow(() -> pessoaDAO.criarPessoa(pessoa));

   
    String sql = "INSERT INTO pessoa (id, cpf, email, nome, telefone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    verify(sqlUtilMock, times(1)).executeInsert(sql, connectionMock, pessoa);
}*/

/*
    @Test
    void testConsultarPessoaPorId() throws SQLException {
        // Dados de exemplo
        String id = "1";

        // Configuração do mock
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        when(connectionMock.prepareStatement(sql)).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

        // Chamada do método
        Pessoa result = pessoaDAO.consultarPessoaPorId(id);

        // Verificações
        verify(connectionMock, times(1)).prepareStatement(sql);
        verify(preparedStatementMock, times(1)).setString(1, id);
        verify(preparedStatementMock, times(1)).executeQuery();
        verify(resultSetMock, times(0)).next();  // ajuste conforme necessário
        // Adicione verificações específicas se necessário
    }
*/
    @Test
    void testAtualizarPessoa() throws SQLException {
        // Dados de exemplo
        Pessoa pessoa = new Pessoa();
        pessoa.setId("1");
        pessoa.setNome("Novo Nome");
        pessoa.setTelefone("987654321");
        pessoa.setEmail("novo@teste.com");
        pessoa.setCpf("98765432101");
        pessoa.setUsername("novoUsuario");
        pessoa.setPassword("novaSenha");

        // Configuração do mock
        String sql = "UPDATE pessoa SET nome = ?, telefone = ?, email = ?, cpf = ?, username = ?, password = ? WHERE id = ?";
        when(connectionMock.prepareStatement(sql)).thenReturn(preparedStatementMock);

        // Chamada do método
        assertDoesNotThrow(() -> pessoaDAO.atualizarPessoa(pessoa));

        // Verificações
        verify(sqlUtilMock, times(1)).executeInsert(sql, connectionMock, pessoa);
    }

    @Test
    void testExcluirPessoa() throws SQLException {
        // Dados de exemplo
        String id = "1";

        // Configuração do mock
        String sql = "DELETE FROM pessoa WHERE id = ?";
        when(connectionMock.prepareStatement(sql)).thenReturn(preparedStatementMock);

        // Chamada do método
        assertDoesNotThrow(() -> pessoaDAO.excluirPessoa(id));

        // Verificações
        verify(preparedStatementMock, times(1)).setString(1, id);
        verify(preparedStatementMock, times(1)).executeUpdate();
    }
}