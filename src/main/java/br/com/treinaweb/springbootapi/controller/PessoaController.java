package br.com.treinaweb.springbootapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.service.PessoaService;

import javax.validation.Valid;

import java.sql.Connection;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPessoa(@PathVariable long id) {
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody Pessoa pessoa) {
        try {
            pessoa = pessoaService.criarPessoa(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        } catch (SQLException e) {
            // Tratar a exceção apropriadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable long id, @Valid @RequestBody Pessoa newPessoa) {
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
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPessoa(@PathVariable long id) {
        try {
            pessoaService.excluirPessoa(id);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            // Tratar a exceção apropriadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

/* 
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.repository.PessoaRepository;

@RestController
public class PessoaController {
    @Autowired
    private PessoaRepository _pessoaRepository;

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public List<Pessoa> Get() {
        return _pessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa", method =  RequestMethod.POST)
    public Pessoa Post(@Valid @RequestBody Pessoa pessoa)
    {
        return _pessoaRepository.save(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa)
    {
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            pessoa.setTelefone(newPessoa.getTelefone()); 
            pessoa.setEmail(newPessoa.getEmail());
            pessoa.setCpf(newPessoa.getCpf());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
*/