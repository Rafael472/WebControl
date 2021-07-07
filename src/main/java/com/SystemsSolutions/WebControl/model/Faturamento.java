package com.SystemsSolutions.WebControl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.SystemsSolutions.WebControl.enums.StatusProduto;

@Entity
@Table(name = "FATURAMENTO")
public class Faturamento {

	@Id
	@Column(name = "ID_FATURAMENTO", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO", unique = false, nullable = false)
	private Date dataCadastro;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_EMISSAO", unique = false, nullable = false)
	private Date dataEmissao;
	
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que R$0,01")
	@DecimalMax(value = "99999999.9999", message = "Valor não pode ser maior que R$99.999.999,9999")
	@NumberFormat(pattern = "#,##0.00##")
	@Column(name = "VALOR", unique = false, nullable = false)
	private double valor;

	@NotBlank(message = "Cliente é obrigatório")
	@Column(name = "CLIENTE_ID", unique = false, nullable = false)
	private Long cliente_id;
	
	@NotBlank(message = "Cliente é obrigatório")
	@Column(name = "CLIENTE", unique = false, nullable = false)
	private String cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
}
