package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.repositories.DespesaRepository;
import br.com.sistema.financeiro.repositories.ReceitaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ResumoServiceTest {

    private final DespesaRepository despesaRepository = mock(DespesaRepository.class);
    private final DespesaService despesaService = new DespesaService(despesaRepository);
    private final ReceitaRepository receitaRepository = mock(ReceitaRepository.class);
    private final ReceitaService receitaService = new ReceitaService(receitaRepository);

    private final ResumoService service = new ResumoService(receitaService, despesaService);



}