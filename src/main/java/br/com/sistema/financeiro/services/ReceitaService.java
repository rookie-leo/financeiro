package br.com.sistema.financeiro.services;

import br.com.sistema.financeiro.entity.Receita;
import br.com.sistema.financeiro.http.models.ReceitaRequest;
import br.com.sistema.financeiro.http.models.ReceitaResponse;
import br.com.sistema.financeiro.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.stream.Stream;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    public ReceitaResponse cadastrar(ReceitaRequest request) {
        Receita receita = request.toModel();
        verificaDuplicidade(receita);

        repository.save(receita);

        return new ReceitaResponse(receita);
    }

    private void verificaDuplicidade(Receita receita) {
        var isEncontrado = repository.findAll()
                .stream()
                .anyMatch(r ->
                        r.equals(receita)
                );

        if (isEncontrado) {
            throw new RuntimeException("Receita já cadastrada!");
        }
    }
}
