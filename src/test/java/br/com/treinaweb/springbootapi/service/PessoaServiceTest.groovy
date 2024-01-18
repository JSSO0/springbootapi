package br.com.treinaweb.springbootapi.service

import br.com.treinaweb.springbootapi.entity.Pessoa
import br.com.treinaweb.springbootapi.implement.PessoaDAO
import org.mockito.Mock
import spock.lang.Specification

class PessoaServiceTest extends Specification {

    @Mock
    PessoaDAO pessoaDAOMock

    def "testar criarPessoa"() {
        given:
        def pessoaParaTeste = new Pessoa(
                id: "1",
                nome: "João",
                telefone: "123456789",
                email: "joao@example.com",
                cpf: "12345678901",
                username: "joao123",
                password: "senha123"
        )
        pessoaDAOMock.criarPessoa(_) >> pessoaParaTeste

        def pessoaService = new PessoaService(pessoaDAOMock)

        when:
        def pessoaRetornada = pessoaService.criarPessoa(pessoaParaTeste)

        then:
        1 * pessoaDAOMock.criarPessoa(pessoaParaTeste)
        pessoaRetornada == pessoaParaTeste
    }
}