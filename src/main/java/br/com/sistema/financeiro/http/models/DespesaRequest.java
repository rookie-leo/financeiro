package br.com.sistema.financeiro.http.models;

import br.com.sistema.financeiro.entity.Despesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DespesaRequest {

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    public DespesaRequest(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Despesa toModel() {
        return new Despesa(this.descricao, this.valor);
    }

}
