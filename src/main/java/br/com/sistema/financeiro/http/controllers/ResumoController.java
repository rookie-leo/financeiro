package br.com.sistema.financeiro.http.controllers;

import br.com.sistema.financeiro.http.models.Resumo;
import br.com.sistema.financeiro.services.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    private ResumoService service;

    @Autowired
    public ResumoController(ResumoService service) {
        this.service = service;
    }

    @GetMapping("/{ano}/{mes}")
    public Resumo resumo(@PathVariable String ano, @PathVariable String mes) {
        return service.resumo(ano, mes);
    }

}
