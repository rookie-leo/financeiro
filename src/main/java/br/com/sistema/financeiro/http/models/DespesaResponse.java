package br.com.sistema.financeiro.http.models;

import br.com.sistema.financeiro.entity.Despesa;

import java.math.BigDecimal;

public class DespesaResponse {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private String data;

    public DespesaResponse(Despesa despesa) {
        this.id = despesa.getId();
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
        this.data = despesa.getData().toString();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }
}
