package br.com.sistema.financeiro.http.controllers;

import br.com.sistema.financeiro.http.models.DespesaRequest;
import br.com.sistema.financeiro.http.models.DespesaResponse;
import br.com.sistema.financeiro.services.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<DespesaResponse> cadastrar(@RequestBody @Valid DespesaRequest request) {
        if(request == null) {
            return ResponseEntity.badRequest().build();
        }

        var despesaResponse = service.cadastrar(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/despesas/{id}")
                .buildAndExpand(despesaResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(despesaResponse);
    }

    @GetMapping()
    public List<DespesaResponse> listar() {
        List<DespesaResponse> responseList = service.listar();

        return responseList;
    }

    @GetMapping("/{id}")
    public DespesaResponse buscarDespesa(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponse> atualizarDespesa(@PathVariable Long id,
                                                            @RequestBody @Valid DespesaRequest request) {
        DespesaResponse response = service.atualizar(id, request);

        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDespesa(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.ok().build();
    }

}
