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
	} //RESOLVER O ERRO DE LOGIN
	
	/*
	 * CÓDIGO COMENTADO PARA IPLEMENTAR O JAVA SECURITY NO PROJETO
	 * 
	@RequestMapping(value = "/login/buscar", method = RequestMethod.GET)
	public String buscar(@ModelAttribute("form") Usuario usuario, Errors errros, RedirectAttributes attributes, HttpServletRequest request, HttpServletResponse response) {

		//String para redirect, se login não efetuado, permanece na pagina de login
		String paginaRedirect = "redirect:/login";
		Seguranca seguranca = new Seguranca();
		seguranca.setStrLogin_Usuario(usuario.getUsuario());
		seguranca.setStrLogin_Senha(usuario.getSenha());
						
		//procurando usuario por coluna usuario
		List<Usuario> usuarioEncontrado = usuarios.findByUsuario(usuario.getUsuario());
		if(usuarioEncontrado.isEmpty()) {
			attributes.addFlashAttribute("mensagem", "Usuário não encontrado.");
			attributes.addFlashAttribute("usuario", usuario);
		}else {
			//comparando se a senha deste usuario encontrado é igual a senha digitada no formulario
			if(usuarioEncontrado.get(0).getSenha().equals(usuario.getSenha())) {
				attributes.addFlashAttribute("usuario", usuarioEncontrado.get(0));
				
				int horasExpiracao = 1;
				Cookie userinfo = new Cookie("userId",""); //declarando cookie
				userinfo.setValue(seguranca.getStrLogin_Usuario()+"|"+seguranca.getStrLogin_Senha()); //setando valor do cookie
				userinfo.setMaxAge(60*60*horasExpiracao); //setando tempo de expiracao do cookie
				userinfo.setPath("/");
				try{
					response.addCookie(userinfo); //adicionando cookie ao navegador
					System.out.println("Quantidade de cookies: " + request.getCookies().length + " -- LoginController");
				}catch(Exception ex) {
					System.out.println("Erro ao adicionar o cookie no navegador. " + ex.getMessage());
				}
				
				paginaRedirect =  "redirect:/inicio/seguranca";//CabecalhoController
			}else {
				attributes.addFlashAttribute("mensagem", "Senha incorreta.");
				attributes.addFlashAttribute("usuario", usuario);
			}
		}
		
		//Se usuário não encontrado ou senha incorreta, permanece na pagina de login, senão, redirect para inicio
		return paginaRedirect;
	}
	*/
}
