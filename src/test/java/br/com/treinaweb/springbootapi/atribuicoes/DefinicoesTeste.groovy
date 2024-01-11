package br.com.treinaweb.springbootapi.atribuicoes

import br.com.treinaweb.springbootapi.entity.Pessoa
import spock.lang.Specification

import java.sql.ResultSet

class DefinicoesTeste extends Specification {

    Definicoes definicoes = new Definicoes()

    def 'shouldExecutemapResultSetToPessoa'() {
        given:
        ResultSet resultSet = Mock(ResultSet)
        resultSet.getString("id") >> "1"
        resultSet.getString("nome") >> "Teste"
        resultSet.getString("telefone") >> "123456789"
        resultSet.getString("email") >> "teste@teste.com"
        resultSet.getString("cpf") >> "12345678901"
        resultSet.getString("username") >> "usuario"
        resultSet.getString("password") >> "senha"

        when:
        Pessoa pessoa = definicoes.mapResultSetToPessoa(resultSet)

        then:
        pessoa.id == "1"
        pessoa.nome == "Teste"
        pessoa.telefone == "123456789"
        pessoa.email == "teste@teste.com"
        pessoa.cpf == "12345678901"
        pessoa.username == "usuario"
        pessoa.password == "senha"
    }

    def 'shouldExecuteCopiarAtributos'() {
        given:
        Pessoa origem = new Pessoa(username: 'user1', password: 'pass1', nome: 'Nome1', telefone: '123456', email: 'user1@example.com', cpf: '12345678901')
        Pessoa destino = new Pessoa()

        when:
        definicoes.copiarAtributos(destino, origem)

        then:
        destino.username == 'user1'
        destino.password == 'pass1'
        destino.nome == 'Nome1'
        destino.telefone == '123456'
        destino.email == 'user1@example.com'
        destino.cpf == '12345678901'
    }
}
