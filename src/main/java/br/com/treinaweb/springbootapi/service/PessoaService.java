package br.com.treinaweb.springbootapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.implement.PessoaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class PessoaService {
    private final PessoaDAO pessoaDAO;

    @Autowired
    public PessoaService(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public List<Pessoa> listarPessoas() throws SQLException {
        return pessoaDAO.listarTodasAsPessoas();
    }

    public Pessoa obterPessoaPorId(long id) throws SQLException {
        return pessoaDAO.consultarPessoaPorId(id);
    }

    public Pessoa criarPessoa(Pessoa pessoa) throws SQLException {
        pessoaDAO.inserirPessoa(pessoa.getId(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getEmail(), pessoa.getCpf());
        return pessoa;
    }

    public Pessoa atualizarPessoa(long id, Pessoa newPessoa) throws SQLException {
        Pessoa pessoaExistente = pessoaDAO.consultarPessoaPorId(id);
        if (pessoaExistente != null) {
            pessoaDAO.atualizarPessoa(id, newPessoa.getNome(), newPessoa.getTelefone(), newPessoa.getEmail(), newPessoa.getCpf());
            pessoaExistente.setNome(newPessoa.getNome());
            pessoaExistente.setTelefone(newPessoa.getTelefone());
            pessoaExistente.setEmail(newPessoa.getEmail());
            pessoaExistente.setCpf(newPessoa.getCpf());
            return pessoaExistente;
        } else {
            return null;
        }
    }

    public void excluirPessoa(long id) throws SQLException {
        pessoaDAO.excluirPessoa(id);
    }
}
