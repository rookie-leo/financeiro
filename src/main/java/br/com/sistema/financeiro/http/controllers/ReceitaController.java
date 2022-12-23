package br.com.sistema.financeiro.http.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @GetMapping()
    public String helloWorld() {
        return "Hello World!";
    }

}
