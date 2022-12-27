package br.com.sistema.financeiro.http.controllers;

import br.com.sistema.financeiro.http.models.ReceitaRequest;
import br.com.sistema.financeiro.http.models.ReceitaResponse;
import br.com.sistema.financeiro.services.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<ReceitaResponse> cadastrar(@RequestBody @Valid ReceitaRequest request) {
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

    @GetMapping()
    public List<ReceitaResponse> listar() {
        List<ReceitaResponse> responseList = service.listar();

        return responseList;
    }

    @GetMapping("/{id}")
    public ReceitaResponse buscarReceita(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaResponse> atualizarReceita(@PathVariable Long id,
                                                            @RequestBody @Valid ReceitaRequest request) {
        ReceitaResponse response = service.atualizar(id, request);

        return ResponseEntity.accepted().body(response);
    }

}
