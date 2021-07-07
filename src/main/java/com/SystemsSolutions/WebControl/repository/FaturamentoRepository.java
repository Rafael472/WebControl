package com.SystemsSolutions.WebControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SystemsSolutions.WebControl.model.Faturamento;

@Repository
public interface FaturamentoRepository extends JpaRepository<Faturamento, Long>{

}
