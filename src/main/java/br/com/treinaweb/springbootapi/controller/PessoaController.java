package br.com.treinaweb.springbootapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.service.PessoaService;

import javax.validation.Valid;



import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        try {
            List<Pessoa> pessoas = pessoaService.listarPessoas();
            return ResponseEntity.ok(pessoas);
        } catch (SQLException e) {
            // Tratar a exceção apropriadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPessoa(@PathVariable String id) {
        try {
            Pessoa pessoa = pessoaService.obterPessoaPorId(id);
            if (pessoa != null) {
                return ResponseEntity.ok(pessoa);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            // Tratar a exceção apropriadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            pessoa = pessoaService.criarPessoa(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        } catch (SQLException e) {
            // Tratar a exceção apropriadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable String id, @Valid @RequestBody Pessoa newPessoa) {
        try {
            Pessoa pessoa = pessoaService.atualizarPessoa(id, newPessoa);
            if (pessoa != null) {
                return ResponseEntity.ok(pessoa);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            // Tratar a exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPessoa(@PathVariable String id) {
        try {
            pessoaService.excluirPessoa(id);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            // Tratar a exceção apropriadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}