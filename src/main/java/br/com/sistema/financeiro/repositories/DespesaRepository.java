package br.com.sistema.financeiro.repositories;

import br.com.sistema.financeiro.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByDescricao(String descricao);
}
