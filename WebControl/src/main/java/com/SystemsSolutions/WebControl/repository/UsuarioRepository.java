package com.SystemsSolutions.WebControl.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SystemsSolutions.WebControl.model.StatusUsuario;
import com.SystemsSolutions.WebControl.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//From deve ser identico ao nome da classe, tal classe deve estar mapeada como Entity
	//nome da coluna deve ser identico ao nome do atributo da classe
	@Query("SELECT u FROM Usuario u WHERE u.usuario = :usuarioParam")
	Usuario findByUsuario(@Param("usuarioParam") String usuario);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :emailParam")
	Usuario findByEmail(@Param("emailParam") String email);

	//edita usuario sem alterar senha
	@Modifying
	@Query("UPDATE Usuario u "
			+ "SET u.usuario 		= :usuarioParam"
			+ ", u.email 			= :emailParam"
			+ ", u.dataAlteracao 	= :dataAltParam"
			+ ", u.horaAlteracao 	= :horaAltParam"
			+ ", u.status 			= :statusParam "
			+ "WHERE u.id_usuario = :idParam")
	Integer EditByUsuario(@Param("usuarioParam") String usuario
						, @Param("emailParam") String email
						, @Param("dataAltParam") Date dataAlteracao
						, @Param("horaAltParam") Date horaAlteracao
						, @Param("statusParam") StatusUsuario status
					, @Param("idParam")Long id);
	
	//edita usuario alterando senha
	@Modifying
	@Query("UPDATE Usuario u "
			+ "SET u.usuario 		= :usuarioParam"
			+ ", u.senha			= :senhaParam"
			+ ", u.email 			= :emailParam"
			+ ", u.dataAlteracao 	= :dataAltParam"
			+ ", u.horaAlteracao 	= :horaAltParam"
			+ ", u.status 			= :statusParam "
			+ "WHERE u.id_usuario = :idParam")
	Integer EditByUsuario(@Param("usuarioParam") String usuario
						, @Param("senhaParam") String senha
						, @Param("emailParam") String email
						, @Param("dataAltParam") Date dataAlteracao
						, @Param("horaAltParam") Date horaAlteracao
						, @Param("statusParam") StatusUsuario status
					, @Param("idParam")Long id);

}