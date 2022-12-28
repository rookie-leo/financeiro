package br.com.sistema.financeiro.repositories;

import br.com.sistema.financeiro.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
