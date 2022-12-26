package br.com.sistema.financeiro.http.models;

import br.com.sistema.financeiro.entity.Receita;

import java.math.BigDecimal;

public class ReceitaRequest {

    private String descricao;
    private BigDecimal valor;

    public ReceitaRequest(String descricao, String valor) {
        this.descricao = descricao;
        this.valor = new BigDecimal(valor);
    }

    public Receita toModel() {
        return new Receita(this.descricao, this.valor);
    }

}
