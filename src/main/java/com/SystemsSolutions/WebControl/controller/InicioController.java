package com.SystemsSolutions.WebControl.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SystemsSolutions.WebControl.model.Seguranca;
import com.SystemsSolutions.WebControl.service.UsuarioServices;

@Controller
public class InicioController {
	Seguranca seguranca = new Seguranca();
	
	@Autowired
	UsuarioServices segurancaServices;

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}
	
	// abre página home
	@RequestMapping({"/", "/home"})
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("Inicio");
		return mv;
	}
	
	// acesso negado
	@RequestMapping("/acesso-negado")
	public ModelAndView acessoNegado(HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("Erro");
		mv.addObject("status", resp.getStatus());
		mv.addObject("error", "Acesso Negado");
		mv.addObject("message", "você não tem permissão para acesso a esta área ou ação.");
		return mv;
	}

}
