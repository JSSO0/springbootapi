package br.com.treinaweb.springbootapi.service;

import br.com.treinaweb.springbootapi.atribuicoes.Definicoes;
import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.implement.PessoaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Pessoa obterPessoaPorId(String id) throws SQLException {
        return pessoaDAO.consultarPessoaPorId(id);

    }

    public Pessoa criarPessoa(Pessoa pessoa) throws SQLException {
        // Verifica se pessoaDAO foi inicializado antes de usá-lo
        if (pessoaDAO == null) {
            throw new IllegalStateException("PessoaDAO não foi inicializado corretamente.");
        }

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
