package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.http.models.DespesaRequest;
import br.com.sistema.financeiro.http.models.DespesaResponse;
import br.com.sistema.financeiro.repositories.DespesaRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void shouldBeThrowNullPointerExceptionWhenRegisteringANewNullDespesa() {
        request = null;
        assertThrows(NullPointerException.class, () -> service.cadastrar(request));
    }

    @Test
    public void shouldGetListOfDespesas() {
        List<DespesaResponse> listaDespesa = service.listar();

        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenIdNotFound() {
        assertThrows(RuntimeException.class, () -> service.buscar(1L));
    }

    @Test
    public void shouldFindADespesaByDescricao() {
        requestBuilder();
        service.buscarPorDescricao("Descrição teste");

        verify(repository, times(1)).findByDescricao(any());
    }

    private DespesaRequest requestBuilder() {
        return new DespesaRequest("Descrição teste", new BigDecimal(50.0), "outros");
    }
}