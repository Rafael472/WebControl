package com.SystemsSolutions.WebControl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SystemsSolutions.WebControl.model.Seguranca;

@Controller
//@RequestMapping("/WebControl")
public class CabecalhoController {

	Seguranca seguranca = new Seguranca();
	
	@RequestMapping("/inicio/seguranca")
	public String cabecalho(RedirectAttributes attributes, HttpServletRequest request) {
		String paginaRedirect = "redirect:/";
		Cookie cookie = seguranca.getUsuario(request);
		if(cookie == null)
			attributes.addFlashAttribute("usuario", "Usuario não encontrado");
		else {
			String[] usuario = cookie.getValue().toString().split("[|]"); //posição 0 = usuario, posição 1 = senha
			attributes.addFlashAttribute("usuario", usuario[0]);
			attributes.addFlashAttribute("empresa", usuario[1]);
		}
		System.out.println("Passou por aqui!   cabecalhocontroller");
		return paginaRedirect;
	}
	
}
