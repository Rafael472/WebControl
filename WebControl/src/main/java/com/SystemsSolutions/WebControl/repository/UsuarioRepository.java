package com.SystemsSolutions.WebControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SystemsSolutions.WebControl.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//From deve ser identico ao nome da classe, tal classe deve estar mapeada como Entity
	//nome da coluna deve ser identico ao nome do atributo da classe
	@Query("SELECT u FROM Usuario u WHERE u.usuario = :usuarioParam")
	Usuario findByUsuario(@Param("usuarioParam") String usuario);
}