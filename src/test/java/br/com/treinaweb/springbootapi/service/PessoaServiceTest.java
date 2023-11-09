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
    Connection connection;
    PessoaService pessoaService;

    @Before

    public void setup() {
        this.pessoaDAO = mock(PessoaDAO.class);
        this.connection = mock(Connection.class);
        this.pessoaService = new PessoaService(connection);
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
        verify(pessoaDAO.listarTodasAsPessoas(), times(1));
        verify(Objects.equals(result, List.of(new Pessoa())));
    }
}