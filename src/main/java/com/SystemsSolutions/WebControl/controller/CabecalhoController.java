package com.SystemsSolutions.WebControl.controller;

import groovy.util.logging.Slf4j;
import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class CabecalhoController {

	private static final String REDIRECT_HOME = "redirect:/";
	private static final String COOKIE_NAME = "userData";
	private static final String ATTR_USUARIO = "usuario";
	private static final String ATTR_EMPRESA = "empresa";
	private static final String ATTR_ERRO = "erro";
	private static final Logger log = LoggerFactory.getLogger(CabecalhoController.class);

	@GetMapping("/inicio/seguranca")
	public String cabecalho(
			RedirectAttributes attributes,
			@CookieValue(value = COOKIE_NAME, required = false) Cookie cookie
	) {
		if (cookie == null || cookie.getValue() == null || !cookie.getValue().contains("|")) {
			log.warn("Cookie '{}' inválido ou ausente", COOKIE_NAME);
			attributes.addFlashAttribute(ATTR_USUARIO, "Usuário não encontrado");
			return REDIRECT_HOME;
		}

		String[] usuario = cookie.getValue().split("[|]");
		if (usuario.length < 2) {
			log.error("Formato do cookie '{}' inválido", COOKIE_NAME);
			attributes.addFlashAttribute(ATTR_ERRO, "Dados inválidos");
			return REDIRECT_HOME;
		}

		// AVISO: Não armazene senhas em cookies (recomendado remover esta parte)
		attributes.addFlashAttribute(ATTR_USUARIO, usuario[0]);
		attributes.addFlashAttribute(ATTR_EMPRESA, usuario[1]);

		return REDIRECT_HOME;
	}
}