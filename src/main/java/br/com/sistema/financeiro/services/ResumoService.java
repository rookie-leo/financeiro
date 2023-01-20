package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.http.models.DespesaResponse;
import br.com.sistema.financeiro.http.models.ReceitaResponse;
import br.com.sistema.financeiro.http.models.Resumo;

import java.math.BigDecimal;
import java.util.List;

public class ResumoService {

    private ReceitaService receita;
    private DespesaService despesa;

    public ResumoService(ReceitaService receita, DespesaService despesa) {
        this.receita = receita;
        this.despesa = despesa;
    }

    public Resumo resumo(String ano, String mes) {
        var receitaResponses = receita.buscarPorMesEAno(ano, mes);
        var despesaResponses = despesa.buscarPorAnoEMes(ano, mes);
        var valorTotalReceitasMes = valorTotalReceitasMes(receitaResponses);
        var valorTotalDespesasMes = valorTotalDespesasMes(despesaResponses);
        var saldoFinalMes = saldoFinalMes(valorTotalReceitasMes, valorTotalDespesasMes);
        valorTotalGastoMesPorCategoria(despesaResponses);

        return null;
    }

    private void valorTotalGastoMesPorCategoria(List<DespesaResponse> despesaResponses) {

    }

    private BigDecimal saldoFinalMes(BigDecimal valorTotalReceitasMes, BigDecimal valorTotalDespesasMes) {
        return valorTotalReceitasMes.subtract(valorTotalDespesasMes);
    }

    private static BigDecimal valorTotalReceitasMes(List<ReceitaResponse> receitaResponses) {
        BigDecimal soma = BigDecimal.ZERO;
        receitaResponses.forEach(r ->
                    soma.add(r.getValor())
                );

        return soma;
    }

    private static BigDecimal valorTotalDespesasMes(List<DespesaResponse> receitaResponses) {
        BigDecimal soma = BigDecimal.ZERO;
        receitaResponses.forEach(d ->
                soma.add(d.getValor())
        );

        return soma;
    }
}
