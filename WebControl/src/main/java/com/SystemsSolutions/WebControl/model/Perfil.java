package com.SystemsSolutions.WebControl.model;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "PERFIS")
public class Perfil extends AbstractEntity {
	
	@Column(name = "DESCRICAO", nullable = false, unique = true)
	private String desc;
	
	public Perfil() {
		super();		
		
	}

	public Perfil(Long id) {
		super.setId(id);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
