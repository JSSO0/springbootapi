package br.com.treinaweb.springbootapi.service;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.implement.PessoaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    private PessoaDAO pessoaDAOMock;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarPessoa() throws SQLException {
        Pessoa pessoaParaTeste = new Pessoa();
        pessoaParaTeste.setId("1");
        pessoaParaTeste.setNome("João");
        pessoaParaTeste.setTelefone("123456789");
        pessoaParaTeste.setEmail("joao@example.com");
        pessoaParaTeste.setCpf("12345678901");
        pessoaParaTeste.setUsername("joao123");
        pessoaParaTeste.setPassword("senha123");


        doNothing().when(pessoaDAOMock).criarPessoa(any());

        Pessoa pessoaRetornada = pessoaService.criarPessoa(pessoaParaTeste);

        verify(pessoaDAOMock, times(1)).criarPessoa(pessoaParaTeste);
    }

}