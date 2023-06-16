package br.com.treinaweb.springbootapi.controller;

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

import br.com.treinaweb.springbootapi.entity.Dados;
import br.com.treinaweb.springbootapi.repository.DadosRepository;

@RestController
public class DadosController {
    @Autowired
    private DadosRepository _dadosRepository;

    @RequestMapping(value = "/dados", method = RequestMethod.GET)
    public List<Dados> Get() {
        return _dadosRepository.findAll();
    }

    @RequestMapping(value = "/dados/{id}", method = RequestMethod.GET)
    public ResponseEntity<Dados> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Dados> dados = _dadosRepository.findById(id);
        if(dados.isPresent())
            return new ResponseEntity<Dados>(dados.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/dados", method =  RequestMethod.POST)
    public Dados Post(@Valid @RequestBody Dados dados)
    {
        return _dadosRepository.save(dados);
    }

    @RequestMapping(value = "/dados/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Dados> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Dados newDados)
    {
        Optional<Dados> oldDados = _dadosRepository.findById(id);
        if(oldDados.isPresent()){
            Dados dados = oldDados.get();
            dados.setCpf(newDados.getCpf());
            dados.setRg(newDados.getRg());
            _dadosRepository.save(dados);
            return new ResponseEntity<Dados>(dados, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/dados/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Dados> dados = _dadosRepository.findById(id);
        if(dados.isPresent()){
            _dadosRepository.delete(dados.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
