package com.SystemsSolutions.WebControl.model;

import com.SystemsSolutions.WebControl.enums.StatusProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

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
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 255, message = "Nome não pode conter mais de 255 caracteres")
	@Column(name="NOME", unique = false, nullable = false)
	private String nome;

	@Size(max = 255, message = "Descrição não pode conter mais de 255 caracteres")
	@Column(name = "DESCRICAO", unique = false, nullable = false)
	private String descricao;
	
	@NotBlank(message = "Unidade de Medida é obrigatória")
	@Size(max = 6, message = "unidade de medida não pode conter mais de 6 caracteres")
	@Column(name = "UN_MEDIDA", unique = false, nullable = false)
	private String unidadeMedida;
	
	@NotNull(message = "Quantidade é obrigatória")
	@DecimalMin(value = "0.0001", message = "Quantidade não pode ser menor que 0,0001")
	@DecimalMax(value = "99999999.9999", message = "Quantidade não pode ser maior que 99.999.999,9999")
	@NumberFormat(pattern = "#,##0.00##")
	@Column(name = "QUANTIDADE", unique = false, nullable = false)
	private double quantidade;
	
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que R$0,01")
	@DecimalMax(value = "99999999.9999", message = "Valor não pode ser maior que R$99.999.999,9999")
	@NumberFormat(pattern = "#,##0.00##")
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

	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@Column(name = "HORA_ALTERACAO", unique = false, nullable = false)
	private Date horaAlteracao;
	
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
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
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

	public Date getHoraAlteracao() {
		return horaAlteracao;
	}
	
	public void setHoraAlteracao(Date horaAlteracao) {
		this.horaAlteracao = horaAlteracao;
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