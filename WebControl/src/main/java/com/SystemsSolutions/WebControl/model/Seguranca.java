package com.SystemsSolutions.WebControl.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.SystemsSolutions.WebControl.service.SegurancaServices;

public class Seguranca {
	
	@Autowired
	SegurancaServices segurancaServices = new SegurancaServices();
	
	private String strLogin_Usuario;
	private String strLogin_Senha;
	
	public String getStrLogin_Usuario() {
		return strLogin_Usuario;
	}
	public void setStrLogin_Usuario(String strLogin_Usuario) {
		this.strLogin_Usuario = strLogin_Usuario;
	}
	public String getStrLogin_Senha() {
		return strLogin_Senha;
	}
	public void setStrLogin_Senha(String strLogin_Senha) {
		this.strLogin_Senha = strLogin_Senha;
	}
	
	public Cookie getUsuario(HttpServletRequest request) {
		System.out.println("Quantidade de cookies: " + request.getCookies().length + " -- Seguranca");
		Cookie userinfo = segurancaServices.getUsuarioCookie(request);
		if(userinfo != null) {
			System.out.println("achou o cookie " + userinfo.getName());
			return userinfo;
		}
		else {
			return null;			
		}
	}
}
