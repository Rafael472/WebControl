package com.SystemsSolutions.WebControl.controller;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		
	private static final String USUARIO_LISTA = "UsuarioLista";
	private static final String REDIRECT_USUARIO_LISTA = "redirect:/usuario";
	private static final String USUARIO_ACAO = "UsuarioAcao";
	private static final String REDIRECT_USUARIO_ACAO = "redirect:/usuario/novo";
	
	@Autowired UsuarioServices usuarioServices;
	@Autowired UsuarioRepository usuarioRepository;
	
	@RequestMapping({"","/"})
	public ModelAndView usuario() {
		ModelAndView mv = new ModelAndView(USUARIO_LISTA);
		mv.addObject("todosUsuarios", usuarioServices.buscarTodos());
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(USUARIO_ACAO);
		mv.addObject(new Usuario());
		return mv;
	}
	
	@RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Usuario usuario, Errors erros, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(USUARIO_ACAO);
		if(erros.hasErrors()) { 
			return mv;
		}
		
		if(usuarioServices.usuarioExiste(usuario)) {
			mv.addObject("classe", "alert alert-warning");
			mv.addObject("mensagem", "Usuário "+ usuario.getUsername() +" já existe!");
			return mv;
		}
		
		try {
			usuarioServices.salvarUsuario(usuario);
			attributes.addFlashAttribute("classe", "alert alert-success");
			attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		} catch (Exception ex) {
			attributes.addFlashAttribute("classe", "alert alert-danger");
			attributes.addFlashAttribute("mensagem", ex.getMessage());
		}
		mv.setViewName(REDIRECT_USUARIO_ACAO);
		return mv;
	}
	
	@RequestMapping(value = "{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		ModelAndView mv = new ModelAndView(USUARIO_ACAO);
		Usuario usuario = usuarioServices.buscaPorId(codigo);
		mv.addObject(usuario);
		mv.addObject("action", "editar");
		return mv;
	}
	
	@RequestMapping(value = "editar/{codigo}", method = RequestMethod.PUT)
	public String editarUsuario(@Validated Usuario usuario, Errors erros, @PathVariable Long codigo) {
		if(erros.hasErrors()) {
			return USUARIO_ACAO;
		}
		usuarioServices.editarPorUsuario(usuario, codigo);
		return REDIRECT_USUARIO_LISTA;
	}
	
	@RequestMapping(value="excluir/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo) {
		usuarioServices.deletar(codigo);
		return REDIRECT_USUARIO_LISTA;
	}
	
	@ModelAttribute("todosStatusUsuario")
	public List<StatusUsuario> todosStatusUsuario() {
		return Arrays.asList(StatusUsuario.values());
	}
}
