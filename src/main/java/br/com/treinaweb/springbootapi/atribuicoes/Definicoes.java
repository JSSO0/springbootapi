package br.com.treinaweb.springbootapi.atribuicoes;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Definicoes {

    public Pessoa mapResultSetToPessoa(@NotNull ResultSet resultSet) throws SQLException {
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

    public void copiarAtributos(@NotNull Pessoa destino, @NotNull Pessoa origem) {
        destino.setUsername(origem.getUsername());
        destino.setPassword(origem.getPassword());
        destino.setNome(origem.getNome());
        destino.setTelefone(origem.getTelefone());
        destino.setEmail(origem.getEmail());
        destino.setCpf(origem.getCpf());
    }
}
