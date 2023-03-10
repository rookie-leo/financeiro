package br.com.sistema.financeiro.http.models;

import br.com.sistema.financeiro.entity.Receita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ReceitaRequest {

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    public ReceitaRequest(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Receita toModel() {
        return new Receita(this.descricao, this.valor);
    }

}
