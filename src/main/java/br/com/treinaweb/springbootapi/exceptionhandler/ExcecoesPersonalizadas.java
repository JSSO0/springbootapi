package br.com.treinaweb.springbootapi.exceptionhandler;

public class ExcecoesPersonalizadas {

    public static class ListarExcessao extends RuntimeException {
        public ListarExcessao(String mensagem) {
            super(mensagem);
        }
    }

    public static class BuscarPessoaExcessao extends RuntimeException {
        public BuscarPessoaExcessao(String mensagem) {
            super(mensagem);
        }
    }

    public static class CriarPessoaExcessao extends RuntimeException {
        public CriarPessoaExcessao(String mensagem) {
            super(mensagem);
        }
    }

    public static class AtualizarPessoaExcessao extends RuntimeException {
        public AtualizarPessoaExcessao(String mensagem) {
            super(mensagem);
        }
    }

    public static class DeletarPessoaExcessao extends RuntimeException {
        public DeletarPessoaExcessao(String mensagem) {
            super(mensagem);
        }
    }
}