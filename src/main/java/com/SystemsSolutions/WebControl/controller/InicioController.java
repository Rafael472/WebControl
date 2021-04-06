package com.SystemsSolutions.WebControl.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SystemsSolutions.WebControl.model.Seguranca;

@Controller
public class InicioController {
	Seguranca seguranca = new Seguranca();
	
	// abre página home
	@RequestMapping({"/", "/home"})
	public ModelAndView login() {
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
