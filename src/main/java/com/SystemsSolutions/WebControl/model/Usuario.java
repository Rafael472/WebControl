package com.SystemsSolutions.WebControl.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.SystemsSolutions.WebControl.enums.StatusUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USUARIO", indexes = {@Index(name = "PK_ID_USUARIO", columnList = "ID_USUARIO")})
public class Usuario {
	
	@Id
	@Column(name = "ID_USUARIO", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	
	@Column(name = "USUARIO", unique = true, nullable = false)
	@NotBlank(message = "Usuario é obrigatório")
	private String username;
	
	@JsonIgnore
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@NotBlank(message = "Email é obrigatório")
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@ManyToMany
	@JoinTable(
		name = "usuarios_tem_perfis", 
				//referencia usuario_id da tabela usuarios_tem_perfis na id_usuario da tabela usuario
		joinColumns = { @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario") },
        inverseJoinColumns = { @JoinColumn(name = "perfil_id", referencedColumnName = "id") }
	)
	private List<Perfil> perfis;
		
	@Column(name = "DATA_CADASTRO", unique = false, nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name = "DATA_ALTERACAO", unique = false, nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;
	
	@Column(name = "HORA_ALTERACAO", unique = false, nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date horaAlteracao;
	
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public Date getHoraAlteracao() {
		return horaAlteracao;
	}
	public void setHoraAlteracao(Date horaAlteracao) {
		this.horaAlteracao = horaAlteracao;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private StatusUsuario status;

	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public StatusUsuario getStatus() {
		return status;
	}
	public void setStatus(StatusUsuario status) {
		this.status = status;
	}
	public boolean isAtivo(){
		return StatusUsuario.ATIVO.equals(this.status);
	}
	public boolean isPendente(){
		return StatusUsuario.PENDENTE.equals(this.status);
	}
	public boolean isBloqueado(){
		return StatusUsuario.BLOQUEADO.equals(this.status);
	}
}
