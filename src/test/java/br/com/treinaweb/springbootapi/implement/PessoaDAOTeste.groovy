package br.com.treinaweb.springbootapi.implement

import br.com.treinaweb.springbootapi.atribuicoes.Definicoes
import br.com.treinaweb.springbootapi.atribuicoes.SqlUtil
import br.com.treinaweb.springbootapi.entity.Pessoa
import org.mockito.Mock
import spock.lang.Specification

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class PessoaDAOTeste extends Specification {


    PessoaDAO pessoaDAO

    @Mock
    Connection connectionMock

    @Mock
    Definicoes definicoesMock

    @Mock
    SqlUtil sqlUtilMock

    @Mock
    PreparedStatement preparedStatementMock

    def setup() {
        pessoaDAO = new PessoaDAO(connectionMock)
        pessoaDAO.pessoaMapper = definicoesMock
        pessoaDAO.sqlutil = sqlUtilMock
    }

    def 'shouldListarTodasAsPessoas'() {
        given:
        def resultSetMock = Mock(ResultSet)
        def pessoaMock = Mock(Pessoa)

        connectionMock.prepareStatement(_) >> preparedStatementMock
        preparedStatementMock.executeQuery() >> resultSetMock
        resultSetMock.next() >> true >> false
        definicoesMock.mapResultSetToPessoa(_) >> pessoaMock

        when:
        def result = pessoaDAO.listarTodasAsPessoas()

        then:
        result.size() == 1
    }

    def 'shouldCriarPessoa'() {
        given:
        def pessoaMock = Mock(Pessoa)

        when:
        pessoaDAO.criarPessoa(pessoaMock as Pessoa)

        then:
        1 * sqlUtilMock.executeInsert(_, _, pessoaMock)
    }

    def 'shouldConsultarPessoaPorId'() {
        given:
        def resultSetMock = Mock(ResultSet)
        def pessoaMock = Mock(Pessoa)

        connectionMock.prepareStatement(_ as String) >> Mock(PreparedStatement)
        preparedStatementMock.executeQuery() >> resultSetMock
        resultSetMock.next() >> true >> false
        definicoesMock.mapResultSetToPessoa(_ as ResultSet) >> pessoaMock

        when:
        def result = pessoaDAO.consultarPessoaPorId('1')

        then:
        result == pessoaMock
    }

    def 'shouldAtualizarPessoa'() {
        given:
        def pessoaMock = Mock(Pessoa)

        when:
        pessoaDAO.atualizarPessoa(pessoaMock)

        then:
        1 * sqlUtilMock.executeInsert(_, _, pessoaMock)
    }

    def 'shouldExcluirPessoa'() {
        given:

        when:
        pessoaDAO.excluirPessoa('1')

        then:
        1 * connectionMock.prepareStatement(_)
        1 * preparedStatementMock.executeUpdate()
    }
}
