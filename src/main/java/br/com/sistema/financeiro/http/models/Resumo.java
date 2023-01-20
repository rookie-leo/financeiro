package br.com.sistema.financeiro.http.models;

import java.math.BigDecimal;

public class Resumo {

    private BigDecimal valorTotalReceitasMes;
    private BigDecimal valorTotalDespesasMes;
    private BigDecimal saldoFinalMes;
    private TotalPorCategoria valorTotalGastoMesPorCategoria;

    public Resumo(BigDecimal valorTotalReceitasMes, BigDecimal valorTotalDespesasMes, BigDecimal saldoFinalMes, TotalPorCategoria valorTotalGastoMesPorCategoria) {
        this.valorTotalReceitasMes = valorTotalReceitasMes;
        this.valorTotalDespesasMes = valorTotalDespesasMes;
        this.saldoFinalMes = saldoFinalMes;
        this.valorTotalGastoMesPorCategoria = valorTotalGastoMesPorCategoria;
    }

    public BigDecimal getValorTotalReceitasMes() {
        return valorTotalReceitasMes;
    }

    public void setValorTotalReceitasMes(BigDecimal valorTotalReceitasMes) {
        this.valorTotalReceitasMes = valorTotalReceitasMes;
    }

    public BigDecimal getValorTotalDespesasMes() {
        return valorTotalDespesasMes;
    }

    public void setValorTotalDespesasMes(BigDecimal valorTotalDespesasMes) {
        this.valorTotalDespesasMes = valorTotalDespesasMes;
    }

    public BigDecimal getSaldoFinalMes() {
        return saldoFinalMes;
    }

    public void setSaldoFinalMes(BigDecimal saldoFinalMes) {
        this.saldoFinalMes = saldoFinalMes;
    }

    public TotalPorCategoria getValorTotalGastoMesPorCategoria() {
        return valorTotalGastoMesPorCategoria;
    }

    public void setValorTotalGastoMesPorCategoria(TotalPorCategoria valorTotalGastoMesPorCategoria) {
        this.valorTotalGastoMesPorCategoria = valorTotalGastoMesPorCategoria;
    }
}
