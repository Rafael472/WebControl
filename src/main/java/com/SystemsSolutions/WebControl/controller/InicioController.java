package com.SystemsSolutions.WebControl.controller;

import com.SystemsSolutions.WebControl.model.Seguranca;
import com.SystemsSolutions.WebControl.service.UsuarioServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InicioController {
	Seguranca seguranca = new Seguranca();
	
	@Autowired
	UsuarioServices segurancaServices;

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}
	
	// abre página home
	@GetMapping({"/", "/home"})
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("Inicio");
		return mv;
	}
	
	// acesso negado
	@GetMapping("/acesso-negado")
	public ModelAndView acessoNegado(HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("Erro");
		mv.addObject("status", resp.getStatus());
		mv.addObject("error", "Acesso Negado");
		mv.addObject("message", "você não tem permissão para acesso a esta área ou ação.");
		return mv;
	}

}
