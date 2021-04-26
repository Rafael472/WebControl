package com.SystemsSolutions.WebControl.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SystemsSolutions.WebControl.model.Usuario;
import com.SystemsSolutions.WebControl.repository.PerfilRepository;
import com.SystemsSolutions.WebControl.repository.UsuarioRepository;
import com.SystemsSolutions.WebControl.service.UsuarioServices;

@Controller
@RequestMapping("/perfis")
public class PerfilController {

private static final String USUARIO_PERMISSOES_ACAO = "UsuarioPerfilAcao";
	
	@Autowired UsuarioServices usuarioServices;
	@Autowired UsuarioRepository usuarioRepository;
	@Autowired PerfilRepository perfilRepository;
	
	@RequestMapping(value = "/{idUsuario}")
	public ModelAndView permissoes(@PathVariable Long idUsuario) {
		ModelAndView mv = new ModelAndView(USUARIO_PERMISSOES_ACAO);
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		
		if(usuario.isPresent()) {
			mv.addObject("perfis", perfilRepository.findAll());
			mv.addObject("perfisUsuario", usuario.get().getPerfis());
			mv.addObject("usuarioId", usuario.get().getId_Usuario());
			mv.addObject("usuario", usuario.get().getUsuario());
		}
		return mv;
	}
	
	@RequestMapping(value = "/{idUsuario}/ativar/{idPerfil}", method = RequestMethod.PUT)
	public @ResponseBody String ativarPermissao(@PathVariable Long idUsuario, @PathVariable Long idPerfil) {
		usuarioServices.inserirRelacao(idUsuario, idPerfil);
		return "Ativo";
	}
	
	@RequestMapping(value = "/{idUsuario}/inativar/{idPerfil}", method = RequestMethod.PUT)
	public @ResponseBody String inativarPermissao(@PathVariable Long idUsuario, @PathVariable Long idPerfil) {
		usuarioServices.deletarRelacao(idUsuario, idPerfil);
		return "Inativo";
	}
}
