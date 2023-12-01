package br.com.treinaweb.springbootapi.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.rowmaps.PessoaMapper;

public class PessoaDAO {
    private Connection connection;

    // Construtor que recebe uma conexão com o banco de dados
    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Pessoa> listarTodasAsPessoas() throws SQLException {
    List<Pessoa> pessoas = new ArrayList<>();
    String sql = "SELECT * FROM pessoa";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(resultSet.getString("id"));
            pessoa.setNome(resultSet.getString("nome"));
            pessoa.setTelefone(resultSet.getString("telefone"));
            pessoa.setEmail(resultSet.getString("email"));
            pessoa.setCpf(resultSet.getString("cpf"));
            pessoa.setUsername(resultSet.getString("username"));
            pessoa.setPassword(resultSet.getString("password"));
            pessoas.add(pessoa);
        }
    }

    return pessoas;
}
public void inserirPessoa(String Id, String nome, String telefone, String email, String cpf, String username, String password) throws SQLException {
    String sql = "INSERT INTO pessoa (id, cpf, email, nome, telefone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, Id);
        preparedStatement.setString(2, cpf);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, nome);
        preparedStatement.setString(5, telefone);
        preparedStatement.setString(6, username);
        preparedStatement.setString(7, password);
        preparedStatement.executeUpdate();
    } catch (Exception e){
        
    }
    
}

    public Pessoa consultarPessoaPorId(String id) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    PessoaMapper pessoaMapper = new PessoaMapper();
                    return pessoaMapper.mapResultSetToPessoa(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public void atualizarPessoa(String id, String nome, String telefone, String email, String cpf, String username, String password) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, telefone = ?, email = ?, cpf = ?, username = ?, password = ? WHERE id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, telefone);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, cpf);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, id);
            preparedStatement.executeUpdate();
        }
    }

    public void excluirPessoa(String id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
