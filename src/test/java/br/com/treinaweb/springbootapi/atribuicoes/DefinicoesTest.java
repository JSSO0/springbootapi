package br.com.treinaweb.springbootapi.atribuicoes;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefinicoesTest {

    @InjectMocks
    private Definicoes definicoes;

    public DefinicoesTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapResultSetToPessoa() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("id")).thenReturn("1");
        when(resultSet.getString("nome")).thenReturn("Exemplo");
        when(resultSet.getString("telefone")).thenReturn("123456789");
        when(resultSet.getString("email")).thenReturn("exemplo@teste.com");
        when(resultSet.getString("cpf")).thenReturn("12345678901");
        when(resultSet.getString("username")).thenReturn("usuario");
        when(resultSet.getString("password")).thenReturn("senha");

        Pessoa pessoa = definicoes.mapResultSetToPessoa(resultSet);

        assertEquals("1", pessoa.getId());
        assertEquals("Exemplo", pessoa.getNome());
        assertEquals("123456789", pessoa.getTelefone());
        assertEquals("exemplo@teste.com", pessoa.getEmail());
        assertEquals("12345678901", pessoa.getCpf());
        assertEquals("usuario", pessoa.getUsername());
        assertEquals("senha", pessoa.getPassword());
    }

    @Test
    public void testCopiarAtributos() {
        Pessoa origem = new Pessoa();
        origem.setUsername("usuarioOrigem");
        origem.setPassword("senhaOrigem");
        origem.setNome("NomeOrigem");
        origem.setTelefone("123456789Origem");
        origem.setEmail("origem@teste.com");
        origem.setCpf("987654321Origem");

        Pessoa destino = new Pessoa();
        destino.setUsername("usuarioDestino");
        destino.setPassword("senhaDestino");
        destino.setNome("NomeDestino");
        destino.setTelefone("987654321Destino");
        destino.setEmail("destino@teste.com");
        destino.setCpf("123456789Destino");

        definicoes.copiarAtributos(destino, origem);

        assertEquals("usuarioOrigem", destino.getUsername());
        assertEquals("senhaOrigem", destino.getPassword());
        assertEquals("NomeOrigem", destino.getNome());
        assertEquals("123456789Origem", destino.getTelefone());
        assertEquals("origem@teste.com", destino.getEmail());
        assertEquals("987654321Origem", destino.getCpf());
    }
}
