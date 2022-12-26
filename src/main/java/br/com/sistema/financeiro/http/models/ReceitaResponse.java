package br.com.sistema.financeiro.http.models;

import br.com.sistema.financeiro.entity.Receita;

import java.math.BigDecimal;

public class ReceitaResponse {

    private Long id;
    private String descricao;
    private String valor;
    private String data;

    public ReceitaResponse(Receita receita) {
        this.id = receita.getId();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor().toString();
        this.data = receita.getData().toString();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }
}
