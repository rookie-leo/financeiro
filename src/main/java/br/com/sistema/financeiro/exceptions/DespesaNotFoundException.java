package br.com.sistema.financeiro.exceptions;

public class DespesaNotFoundException extends RuntimeException {

    public DespesaNotFoundException(String message) {
        super(message);
    }
}
