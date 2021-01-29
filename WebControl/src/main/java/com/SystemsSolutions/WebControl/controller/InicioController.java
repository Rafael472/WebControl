package com.SystemsSolutions.WebControl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SystemsSolutions.WebControl.model.Seguranca;

@Controller
//@RequestMapping("/WebControl")
public class InicioController {
	Seguranca seguranca = new Seguranca();
	
	@RequestMapping({"/", "/inicio"})
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("Inicio");
		return mv;
	}
}
