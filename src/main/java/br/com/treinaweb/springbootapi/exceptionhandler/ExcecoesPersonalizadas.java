package br.com.treinaweb.springbootapi.exceptionhandler;

public class ExcecoesPersonalizadas {

    public static class ListarExcessao extends RuntimeException {
        public ListarExcessao(String mensagem) {
            super(mensagem);
        }
    }

    public static class MostrarExcessao extends RuntimeException {
        public MostrarExcessao(String mensagem) {
            super(mensagem);
        }
    }
}