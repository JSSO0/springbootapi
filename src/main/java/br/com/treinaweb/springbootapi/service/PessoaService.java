package br.com.treinaweb.springbootapi.service;

import java.util.List;

import br.com.treinaweb.springbootapi.atribuicoes.Definicoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.implement.PessoaDAO;

import java.sql.SQLException;

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

    public Pessoa obterPessoaPorId(String id) throws SQLException {
        return pessoaDAO.consultarPessoaPorId(id);

    }

    public Pessoa criarPessoa(Pessoa pessoa) throws SQLException {
        pessoaDAO.criarPessoa(pessoa);
        return pessoa;
    }

    public Pessoa atualizarPessoa(String id, Pessoa newPessoa) throws SQLException {
        Pessoa pessoaExistente = pessoaDAO.consultarPessoaPorId(id);
        if (pessoaExistente != null) {
            Definicoes pessoaMapper = new Definicoes();
            pessoaDAO.atualizarPessoa(new Pessoa());
            pessoaMapper.copiarAtributos(pessoaExistente, newPessoa);
            return pessoaExistente;
        } else {
            return null;
        }
    }

    public void excluirPessoa(String id) throws SQLException {
        pessoaDAO.excluirPessoa(id);
    }
}
