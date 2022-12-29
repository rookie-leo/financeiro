package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.entity.Despesa;
import br.com.sistema.financeiro.entity.enums.Categoria;
import br.com.sistema.financeiro.http.models.DespesaRequest;
import br.com.sistema.financeiro.repositories.DespesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DespesaServiceTest {

    private DespesaRequest request = requestBuilder();
    private final DespesaRepository repository = mock(DespesaRepository.class);
    private final DespesaService service = new DespesaService(repository);


    @Test
    public void shouldRegisterNewDespesa() {
        service.cadastrar(request);

        verify(repository, times(1)).save(any());
    }

    @Test
    public void shouldBeThrowNullPointerExceptionWhenRegisteringANewNullDepesa() {
        request = null;
        assertThrows(NullPointerException.class, () -> service.cadastrar(request));
    }

    private DespesaRequest requestBuilder() {
        return new DespesaRequest("Descrição teste", new BigDecimal(50.0), "outros");
    }
}