package com.SystemsSolutions.WebControl.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SystemsSolutions.WebControl.model.StatusUsuario;
import com.SystemsSolutions.WebControl.model.Usuario;
import com.SystemsSolutions.WebControl.repository.UsuarioRepository;
import com.SystemsSolutions.WebControl.service.UsuarioServices;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired UsuarioServices usuarioServices;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject("todosStatusUsuario", StatusUsuario.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("form") Usuario usuario, Errors errros, RedirectAttributes attributes) {
		Date data = new Date(System.currentTimeMillis());
		usuario.setDataAlteracao(data);	//GET DATA
		usuario.setHoraAlteracao(data); //GET HORA
		try {
			usuarioServices.salvarUsuario(usuario);
			attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		} catch (Exception ex) {
			attributes.addFlashAttribute("mensagem", ex.getMessage());
		}
		return "redirect:/usuario/novo";
	}

	@ModelAttribute("todosStatusUsuario")
	public List<StatusUsuario> todosStatusUsuario() {
		return Arrays.asList(StatusUsuario.values());
	}

	
}
