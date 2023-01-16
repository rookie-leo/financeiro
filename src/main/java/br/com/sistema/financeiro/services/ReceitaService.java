package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.entity.Receita;
import br.com.sistema.financeiro.http.models.ReceitaRequest;
import br.com.sistema.financeiro.http.models.ReceitaResponse;
import br.com.sistema.financeiro.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    public ReceitaResponse cadastrar(ReceitaRequest request) {
        verificaDuplicidade(request);
        Receita receita = request.toModel();

        repository.save(receita);

        return new ReceitaResponse(receita);
    }

    private void verificaDuplicidade(ReceitaRequest request) {
        var isEncontrado = repository.findAll()
                .stream()
                .anyMatch(receita ->
                        receita.getData().getMonth().equals(LocalDateTime.now().getMonth()) &&
                                receita.getDescricao().equals(request.getDescricao())
                );

        if (isEncontrado) {
            throw new RuntimeException("Receita já cadastrada!");
        }
    }

    public List<ReceitaResponse> listar() {
        List<ReceitaResponse> responseList = new ArrayList<>();

        repository.findAll()
                .forEach(receita -> {
                    responseList.add(new ReceitaResponse(receita));
                });

        return responseList;
    }

    public ReceitaResponse buscar(Long id) {
        return new ReceitaResponse(repository.findById(id).orElseThrow(() ->
                new RuntimeException("Id não encontrado!")
        ));
    }

    public List<ReceitaResponse> buscarPorDescricao(String descricao) {
        List<ReceitaResponse> responseList = new ArrayList<>();

        repository.findByDescricao(descricao)
                .forEach(receita -> {
                    responseList.add(new ReceitaResponse(receita));
                });

        return responseList;
    }

    public List<ReceitaResponse> buscarPorMesEAno(String ano, String mes) {
        List<ReceitaResponse> responseList = new ArrayList<>();

        repository.findByData(ano, mes)
                .forEach(receita -> {
                    responseList.add(new ReceitaResponse(receita));
                });

        return responseList;
    }


    public ReceitaResponse atualizar(Long id, ReceitaRequest request) {
        verificaDuplicidade(request);
        Receita receita = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Id não encontrado!")
        );

        receita.setValor(request.getValor());
        receita.setDescricao(request.getDescricao());
        receita.setData(LocalDateTime.now());

        repository.save(receita);

        return new ReceitaResponse(receita);
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RuntimeException("Id informado não encontrado!");
        }
    }

}
