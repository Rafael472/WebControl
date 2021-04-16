package com.SystemsSolutions.WebControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SystemsSolutions.WebControl.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
