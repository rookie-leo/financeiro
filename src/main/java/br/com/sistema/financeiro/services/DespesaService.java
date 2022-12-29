package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.entity.Despesa;
import br.com.sistema.financeiro.http.models.DespesaRequest;
import br.com.sistema.financeiro.http.models.DespesaResponse;
import br.com.sistema.financeiro.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DespesaService {

    private DespesaRepository repository;

    @Autowired
    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    public DespesaResponse cadastrar(DespesaRequest request) {
        verificaDuplicidade(request);
        Despesa despesa = request.toModel();

        repository.save(despesa);

        return new DespesaResponse(despesa);
    }

    private void verificaDuplicidade(DespesaRequest request) {
        var isEncontrado = repository.findAll()
                .stream()
                .anyMatch(despesa ->
                        despesa.getData().getMonth().equals(LocalDateTime.now().getMonth()) &&
                                despesa.getDescricao().equals(request.getDescricao())
                );

        if (isEncontrado) {
            throw new RuntimeException("Despesa já cadastrada!");
        }
    }

    public List<DespesaResponse> listar() {
        List<DespesaResponse> responseList = new ArrayList<>();

        repository.findAll()
                .forEach(despesa -> {
                    responseList.add(new DespesaResponse(despesa));
                });

        return responseList;
    }

    public DespesaResponse buscar(Long id) {
        return new DespesaResponse(repository.findById(id).orElseThrow(() ->
                new RuntimeException("Id não encontrado!")
        ));
    }

    public DespesaResponse atualizar(Long id, DespesaRequest request) {
        verificaDuplicidade(request);
        Despesa despesa = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Id não encontrado!")
        );

        despesa.setValor(request.getValor());
        despesa.setDescricao(request.getDescricao());
        despesa.setData(LocalDateTime.now());

        repository.save(despesa);

        return new DespesaResponse(despesa);
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RuntimeException("Id informado não encontrado!");
        }
    }
}
