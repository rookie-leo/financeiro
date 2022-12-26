package br.com.sistema.financeiro.http.controllers;

import br.com.sistema.financeiro.http.models.ReceitaRequest;
import br.com.sistema.financeiro.http.models.ReceitaResponse;
import br.com.sistema.financeiro.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<ReceitaResponse> cadastrar(@RequestBody ReceitaRequest request) {
        if(request == null) {
            return ResponseEntity.badRequest().build();
        }

        var receitaResponse = service.cadastrar(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/receitas/{id}")
                .buildAndExpand(receitaResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(receitaResponse);
    }

}
