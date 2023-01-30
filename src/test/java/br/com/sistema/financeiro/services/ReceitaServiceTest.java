package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.http.models.ReceitaRequest;
import br.com.sistema.financeiro.http.models.ReceitaResponse;
import br.com.sistema.financeiro.repositories.ReceitaRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReceitaServiceTest {


    private ReceitaRequest request = requestBuilder();
    private final ReceitaRepository repository = mock(ReceitaRepository.class);
    private final ReceitaService service = new ReceitaService(repository);


    @Test
    public void shouldRegisterNewReceita() {
        service.cadastrar(request);

        verify(repository, times(1)).save(any());
    }

    @Test
    public void shouldBeThrowNullPointerExceptionWhenRegisteringANewNullReceita() {
        request = null;
        assertThrows(NullPointerException.class, () -> service.cadastrar(request));
    }

    @Test
    public void shouldGetListOfReceitas() {
        List<ReceitaResponse> listaReceita = service.listar();

        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenIdNotFound() {
        assertThrows(RuntimeException.class, () -> service.buscar(1L));
    }

    @Test
    public void shouldFindAReceitaByDescricao() {
        requestBuilder();
        service.buscarPorDescricao("Descrição teste");

        verify(repository, times(1)).findByDescricao(any());
    }

    private ReceitaRequest requestBuilder() {
        return new ReceitaRequest("Descrição teste", new BigDecimal(50.0));
    }

}