package com.SystemsSolutions.WebControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SystemsSolutions.WebControl.model.UnidadeMedida;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long>{

	@Query("SELECT u FROM UnidadeMedida u WHERE u.sigla = :sigla")
	UnidadeMedida findBySigla(@Param("sigla") String sigla);

	@Query("SELECT u FROM UnidadeMedida u WHERE u.descricao = :descricao")
	Object findByDescricao(@Param("descricao") String descricao);

}
