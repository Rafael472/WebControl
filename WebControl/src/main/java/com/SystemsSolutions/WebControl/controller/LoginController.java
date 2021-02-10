package com.SystemsSolutions.WebControl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SystemsSolutions.WebControl.repository.UsuarioRepository;
import com.SystemsSolutions.WebControl.service.UsuarioServices;

@Controller
//@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	UsuarioServices segurancaServices;

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}
}
