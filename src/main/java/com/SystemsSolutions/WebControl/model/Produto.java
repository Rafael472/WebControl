package com.SystemsSolutions.WebControl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name="PRODUTO")
public class Produto {

	@Id
	@Column(name = "ID_PRODUTO", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_produto;
	
	@NotBlank(message = "Código é obrigatório")
	@Size(max = 255, message = "O código não pode conter mais de 255 caracteres")
	@Column(name = "CODIGO", unique = true, nullable = false)
	private String codigo;
	
	@NotBlank(message = "Descrição é obrigatória")
	@Size(max = 255, message = "A descrição não pode conter mais de 255 caracteres")
	@Column(name = "DESCRICAO", unique = false, nullable = false)
	private String descricao;
	
	@Size(max = 6, message = "A unidade de medida não pode conter mais de 6 caracteres")
	@Column(name = "UN_MEDIDA", unique = false, nullable = false)
	private String unidadeMedida;
	
	@DecimalMin(value = "0.0001", message = "Quantidade não pode ser menor que R$0,0001")
	@DecimalMax(value = "99999999.9999", message = "Quantidade não pode ser maior que R$99.999.999,9999")
	@Column(name = "QUANTIDADE", unique = false, nullable = false)
	private double quantidade;
	
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.0001", message = "Valor não pode ser menor que R$0,0001")
	@DecimalMax(value = "99999999.9999", message = "Valor não pode ser maior que R$99.999.999,9999")
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "VALOR", unique = false, nullable = false)
	private double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private StatusProduto status;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO", unique = false, nullable = false)
	private Date dataCadastro;
	
	@Column(name = "USUARIO_CADASTRO", unique = false, nullable = false)
	private String usuarioCadastro;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ALTERACAO", unique = false, nullable = false)
	private Date dataAlteracao;

	@Column(name = "USUARIO_ALTERACAO", unique = false, nullable = false)
	private String usuarioAlteracao;

	public Long getId_produto() {
		return id_produto;
	}

	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidade_medida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(String usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public StatusProduto getStatus() {
		return status;
	}

	public void setStatus(StatusProduto status) {
		this.status = status;
	}
	
	public boolean isAtivo(){
		return StatusProduto.ATIVO.equals(this.status);
	}
	public boolean isPendente(){
		return StatusProduto.PENDENTE.equals(this.status);
	}
	public boolean isBloqueado(){
		return StatusProduto.BLOQUEADO.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_produto == null) ? 0 : id_produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id_produto == null) {
			if (other.id_produto != null)
				return false;
		} else if (!id_produto.equals(other.id_produto))
			return false;
		return true;
	}
}