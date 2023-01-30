package br.com.sistema.financeiro.exceptions;

public class ReceitaNotFoundException extends RuntimeException {

    public ReceitaNotFoundException(String message) {
        super(message);
    }
}
