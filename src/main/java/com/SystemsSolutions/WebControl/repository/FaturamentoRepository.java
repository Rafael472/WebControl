package com.SystemsSolutions.WebControl.repository;

import com.SystemsSolutions.WebControl.model.Faturamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturamentoRepository extends JpaRepository<Faturamento, Long>{

}
