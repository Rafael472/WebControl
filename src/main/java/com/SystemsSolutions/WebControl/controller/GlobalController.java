package com.SystemsSolutions.WebControl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.SystemsSolutions.WebControl.model.Perfil;
import com.SystemsSolutions.WebControl.repository.PerfilRepository;

@ControllerAdvice
public class GlobalController {
	
	@Autowired PerfilRepository perfilRepository;
	
	@ModelAttribute("permissoes")
	public List<Perfil> permissoes() {
		return perfilRepository.findAll();
	}
}
