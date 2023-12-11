package br.com.treinaweb.springbootapi.service;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.implement.PessoaDAO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {
    @Mock
    private PessoaDAO pessoaDAOMock;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAtualizarPessoa() throws SQLException {
        Pessoa pessoaExistente = getPessoaDeExemplo();
        Pessoa newPessoa = getNovaPessoaDeExemplo();

        // Mockando o comportamento do PessoaDAO
        when(pessoaDAOMock.consultarPessoaPorId("1")).thenReturn(pessoaExistente);

        // Chamando o método do serviço
        Pessoa resultado = pessoaService.atualizarPessoa("1", newPessoa);

        // Verificando se o método do PessoaDAO foi chamado
        verify(pessoaDAOMock, times(1)).consultarPessoaPorId("1");

        // Verificando se o resultado é a pessoa existente
        assertEquals(pessoaExistente, resultado);

        // Verificando se o método atualizarPessoa foi chamado no DAO
        verify(pessoaDAOMock, times(1)).atualizarPessoa(any());
    }

    // Métodos de apoio para criar dados de exemplo
    private Pessoa getPessoaDeExemplo() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId("1");
        pessoa.setNome("Exemplo");
        // Outros atributos...
        return pessoa;
    }

    private Pessoa getNovaPessoaDeExemplo() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId("1");
        pessoa.setNome("Novo Exemplo");
        // Outros atributos...
        return pessoa;
    }
}