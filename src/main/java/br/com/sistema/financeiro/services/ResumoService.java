package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.http.models.DespesaResponse;
import br.com.sistema.financeiro.http.models.ReceitaResponse;
import br.com.sistema.financeiro.http.models.Resumo;
import br.com.sistema.financeiro.http.models.TotalPorCategoria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
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
        var totalPorCategoria = valorTotalGastoMesPorCategoria(despesaResponses);

        return new Resumo(valorTotalReceitasMes, valorTotalDespesasMes, saldoFinalMes, totalPorCategoria);
    }

    private TotalPorCategoria valorTotalGastoMesPorCategoria(List<DespesaResponse> despesaResponses) {
        TotalPorCategoria totalPorCategoria = new TotalPorCategoria();
        despesaResponses.forEach(despesa ->
                totalPorCategoria.calculadoraCategoria(despesa.getCategoria(), despesa.getValor())
                );
        return totalPorCategoria;
    }

    private BigDecimal saldoFinalMes(BigDecimal valorTotalReceitasMes, BigDecimal valorTotalDespesasMes) {
        return valorTotalReceitasMes.subtract(valorTotalDespesasMes);
    }

    private static BigDecimal valorTotalReceitasMes(List<ReceitaResponse> receitaResponses) {
        BigDecimal soma = BigDecimal.ZERO;

        for (ReceitaResponse response : receitaResponses) {
            soma = soma.add(response.getValor());
        }

        return soma;
    }

    private static BigDecimal valorTotalDespesasMes(List<DespesaResponse> despesaResponses) {
        BigDecimal soma = BigDecimal.ZERO;

        for (DespesaResponse despesa : despesaResponses) {
            soma = soma.add(despesa.getValor());
        }

        return soma;
    }
}
