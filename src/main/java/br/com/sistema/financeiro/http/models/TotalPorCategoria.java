package br.com.sistema.financeiro.http.models;

import br.com.sistema.financeiro.entity.enums.Categoria;

import java.math.BigDecimal;

public class TotalPorCategoria {

    private BigDecimal alimentacao = BigDecimal.ZERO;
    private BigDecimal saude = BigDecimal.ZERO;
    private BigDecimal moradia = BigDecimal.ZERO;
    private BigDecimal transporte = BigDecimal.ZERO;
    private BigDecimal educacao = BigDecimal.ZERO;
    private BigDecimal lazer = BigDecimal.ZERO;
    private BigDecimal imprevistos = BigDecimal.ZERO;
    private BigDecimal outros = BigDecimal.ZERO;

    public void calculadoraCategoria(Categoria categoria, BigDecimal valor) {
        if (categoria.equals(Categoria.ALIMENTACAO)) alimentacao = alimentacao.add(valor);
        else if (categoria.equals(Categoria.SAUDE)) saude = saude.add(valor);
        else if (categoria.equals(Categoria.MORADIA)) moradia = moradia.add(valor);
        else if (categoria.equals(Categoria.TRANSPORTE)) transporte = transporte.add(valor);
        else if (categoria.equals(Categoria.EDUCACAO)) educacao = educacao.add(valor);
        else if (categoria.equals(Categoria.LAZER)) lazer = lazer.add(valor);
        else if (categoria.equals(Categoria.IMPREVISTOS)) imprevistos = imprevistos.add(valor);
        else if (categoria.equals(Categoria.OUTROS)) outros = outros.add(valor);
    }

    public BigDecimal getAlimentacao() {
        return alimentacao;
    }

    public BigDecimal getSaude() {
        return saude;
    }

    public BigDecimal getMoradia() {
        return moradia;
    }

    public BigDecimal getTransporte() {
        return transporte;
    }

    public BigDecimal getEducacao() {
        return educacao;
    }

    public BigDecimal getLazer() {
        return lazer;
    }

    public BigDecimal getImprevistos() {
        return imprevistos;
    }

    public BigDecimal getOutros() {
        return outros;
    }
}
