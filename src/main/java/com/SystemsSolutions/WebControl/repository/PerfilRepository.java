package com.SystemsSolutions.WebControl.repository;

import com.SystemsSolutions.WebControl.model.Perfil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO USUARIOS_TEM_PERFIS (USUARIO_ID, PERFIL_ID) VALUES (:idUsuario, :idPerfil)", nativeQuery = true)
	Integer inserir(@Param("idUsuario") Long idUsuario, @Param("idPerfil") Long idPerfil);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM USUARIOS_TEM_PERFIS WHERE USUARIO_ID = :idUsuario AND PERFIL_ID = :idPerfil", nativeQuery = true)
	void deletar(Long idUsuario, Long idPerfil);

}
