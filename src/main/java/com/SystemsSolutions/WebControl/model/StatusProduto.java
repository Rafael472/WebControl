package com.SystemsSolutions.WebControl.model;

public enum StatusProduto {
	ATIVO("Ativo"),
	PENDENTE("Pendente"),
	BLOQUEADO("Bloqueado");
	
	private String descricao;
	
	StatusProduto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
