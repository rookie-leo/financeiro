package br.com.sistema.financeiro.repositories;

import br.com.sistema.financeiro.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByDescricao(String descricao);

    @Query("select r from Receita r where date_format(r.dataEntrada, '%Y-%m') = :dataFmt")
    List<Receita> findReceitaDataEntrada(@Param("dataFmt") String dataFmt);
}
