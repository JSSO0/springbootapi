package br.com.treinaweb.springbootapi.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

import br.com.treinaweb.springbootapi.exceptionhandler.GlobalException.*;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        // Tratar a exceção, logar, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Erro interno no servidor" + e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Tratar a exceção, logar, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Erro interno no servidor" + e);
    }

    @ExceptionHandler(ExcecoesPersonalizadas.ListarExcessao.class)
    public ResponseEntity<String> handleListarExcessao(ExcecoesPersonalizadas.ListarExcessao e) {
        // Tratar a exceção, logar, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar Pessoas"+ e.getMessage());
    }

    @ExceptionHandler(ExcecoesPersonalizadas.BuscarPessoaExcessao.class)
    public ResponseEntity<String> handleListarExcessao(ExcecoesPersonalizadas.BuscarPessoaExcessao e) {
        // Tratar a exceção, logar, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar pessoa Específica"+ e.getMessage());
    }

    @ExceptionHandler(ExcecoesPersonalizadas.CriarPessoaExcessao.class)
    public ResponseEntity<String> handleListarExcessao(ExcecoesPersonalizadas.CriarPessoaExcessao e) {
        // Tratar a exceção, logar, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário, verifique os dados!"+ e.getMessage());
    }

    @ExceptionHandler(ExcecoesPersonalizadas.AtualizarPessoaExcessao.class)
    public ResponseEntity<String> handleListarExcessao(ExcecoesPersonalizadas.AtualizarPessoaExcessao e) {
        // Tratar a exceção, logar, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário, verifique os dados!"+ e.getMessage());
    }

    @ExceptionHandler(ExcecoesPersonalizadas.DeletarPessoaExcessao.class)
    public ResponseEntity<String> handleListarExcessao(ExcecoesPersonalizadas.DeletarPessoaExcessao e) {
        // Tratar a exceção, logar, etc.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário, verifique os dados!"+ e.getMessage());
    }


}
