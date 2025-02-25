package com.SystemsSolutions.WebControl.repository;

import com.SystemsSolutions.WebControl.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p WHERE p.codigo = :codigo")
	Produto findByCodigo(@Param("codigo") String codigo);

}
