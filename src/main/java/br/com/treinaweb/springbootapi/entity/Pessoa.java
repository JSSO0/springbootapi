package br.com.treinaweb.springbootapi.entity;


import java.util.Objects;
import java.util.UUID;


public class Pessoa {
    private String id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String username;
    private String password;





    public Pessoa() {
        this.id = UUID.randomUUID().toString();
    }

    // Getter e Setter para o novo ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id &&
                Objects.equals(nome, pessoa.nome) &&
                Objects.equals(telefone, pessoa.telefone) &&
                Objects.equals(email, pessoa.email) &&
                Objects.equals(cpf, pessoa.cpf) &&
                Objects.equals(username, pessoa.username) &&
                Objects.equals(password, pessoa.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone, email, cpf, username, password);
    }
}