package com.SystemsSolutions.WebControl.model;

public enum StatusUsuario {
	ATIVO("Ativo"),
	PENDENTE("Pendente"),
	BLOQUEADO("Bloqueado");
	
	private String descricao;
	
	StatusUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
