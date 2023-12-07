package br.com.treinaweb.springbootapi.rowmaps;

import br.com.treinaweb.springbootapi.entity.Pessoa;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaMapper {

    public Pessoa mapResultSetToPessoa(ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(resultSet.getString("id"));
        pessoa.setNome(resultSet.getString("nome"));
        pessoa.setTelefone(resultSet.getString("telefone"));
        pessoa.setEmail(resultSet.getString("email"));
        pessoa.setCpf(resultSet.getString("cpf"));
        pessoa.setUsername(resultSet.getString("username"));
        pessoa.setPassword(resultSet.getString("password"));
        return pessoa;
    }
}
