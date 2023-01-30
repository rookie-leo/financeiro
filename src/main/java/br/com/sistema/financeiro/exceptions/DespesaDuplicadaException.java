package br.com.sistema.financeiro.exceptions;

public class DespesaDuplicadaException extends RuntimeException {

    public DespesaDuplicadaException(String message) {
        super(message);
    }
}
