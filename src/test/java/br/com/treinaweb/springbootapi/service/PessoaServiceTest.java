package br.com.treinaweb.springbootapi.service;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.implement.PessoaDAO;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {
    PessoaDAO pessoaDAO;

    PessoaService pessoaService;

    @Before

    public void setup() {
        this.pessoaDAO = mock(PessoaDAO.class);
        this.pessoaService = new PessoaService(pessoaDAO);
    }

    @Test
    public void shouldReturnListPessoa() throws SQLException {
        //given:
        when(pessoaDAO.listarTodasAsPessoas()).thenReturn(List.of(new Pessoa()));

        //when:
        //quando listar pessoas for chamado
        List<Pessoa> result = pessoaService.listarPessoas();
        //then:
        //verifica se pessoaDAO.Listartodasaspessoas foi chamada uma vez
        //verifica se foi retornado uma lista de pessoas
        verify(pessoaDAO, times(1)).listarTodasAsPessoas();
        //verify(pessoaDAO.listarTodasAsPessoas(), times(1));
        assertEquals(List.of(new Pessoa()), result);
        //verify(Objects.equals(result, List.of(new Pessoa())));
    }

    @Test
    public void shouldReturnObterPessoaId() throws SQLException{
        // Suponhamos que você tem um registro de Pessoa com id = 1 no seu banco de dados
        long idExistente = 1;
        Pessoa pessoaExistente = pessoaService.obterPessoaPorId(idExistente);
        // Verifica se a Pessoa foi retornada corretamente
        assertEquals(idExistente, pessoaExistente.getId());
        // Adicione mais verificações se necessário, comparando outros atributos da Pessoa

        // Suponhamos que você não tenha um registro de Pessoa com id = 999 no seu banco de dados
        long idInexistente = 999;
        Pessoa pessoaInexistente = pessoaService.obterPessoaPorId(idExistente);
        // Verifica se a Pessoa é nula quando não existe no banco de dados
        assertNull(pessoaInexistente);
    }
}