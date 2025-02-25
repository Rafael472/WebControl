package com.SystemsSolutions.WebControl.controller;

import com.SystemsSolutions.WebControl.model.UnidadeMedida;
import com.SystemsSolutions.WebControl.repository.UnidadeMedidaRepository;
import com.SystemsSolutions.WebControl.service.UnidadeMedidaServices;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/unidadeMedida")
public class UnidadeMedidaController {


	private static final String UNIDADE_MEDIDA_LISTA = "UnidadeMedidaLista";
	private static final String UNIDADE_MEDIDA_ACAO = "UnidadeMedidaAcao";
	private static final String REDIRECT_UNIDADE_MEDIDA_LISTA = "redirect:/unidadeMedida";
	private static final String REDIRECT_UNIDADE_MEDIDA_NOVO = "redirect:/unidadeMedida/novo";
	
	@Autowired UnidadeMedidaRepository unidadeMedidaRepository;
	@Autowired UnidadeMedidaServices unidadeMedidaServices;
	
	@RequestMapping(value = {"","/"})
	public ModelAndView unidadeMedida() {
		ModelAndView mv = new ModelAndView(UNIDADE_MEDIDA_LISTA);
		return mv;
	}
	
	@RequestMapping(value = "/novo")
	public ModelAndView unidadeMedidaNovo() {
		ModelAndView mv = new ModelAndView(UNIDADE_MEDIDA_ACAO);
		mv.addObject(new UnidadeMedida());
		return mv;
	}
	
	@RequestMapping(value = "/salvarUnidade", method = RequestMethod.POST)
	public ModelAndView unidadeMedidaSalvar(@Validated UnidadeMedida unidadeMedida, Errors erros, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(UNIDADE_MEDIDA_ACAO);
		if(erros.hasErrors()) {
			return mv;
		}
		if(unidadeMedidaServices.unidadeMedidaExiste(unidadeMedida)) {
			mv.addObject("classe", "alert alert-warning");
			mv.addObject("mensagem", "Sigla ou Descrição já existe!");
			return mv;
		}
		
		try {
			unidadeMedidaServices.salvar(unidadeMedida);
			attributes.addFlashAttribute("classe", "alert alert-success");
			attributes.addFlashAttribute("mensagem", "Unidade de Medida salva com sucesso!");
		}catch (Exception ex) {
			attributes.addFlashAttribute("classe", "alert alert-danger");
			attributes.addFlashAttribute("mensagem", ex.getMessage());
		}
		mv.setViewName(REDIRECT_UNIDADE_MEDIDA_NOVO);
		return mv;
	}
	
	@RequestMapping(value = "/{idUnidade}")
	public ModelAndView editar(@PathVariable Long idUnidade) {
		ModelAndView mv = new ModelAndView(UNIDADE_MEDIDA_ACAO);
		Optional<UnidadeMedida> unidade = unidadeMedidaRepository.findById(idUnidade);
		
		mv.addObject("action", "editar");
		mv.addObject(unidade.get());
		return mv;
	}
	
	@RequestMapping(value = "/editar/", method = RequestMethod.PUT)
	public ModelAndView editarUnidade(@Validated UnidadeMedida unidadeMedida, Errors erros, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(UNIDADE_MEDIDA_ACAO);

		if(erros.hasErrors()) {
			mv.addObject(unidadeMedida);
			mv.addObject("action", "editar");
			return mv;
		}
		
		unidadeMedidaServices.salvar(unidadeMedida);
		mv.setViewName(REDIRECT_UNIDADE_MEDIDA_LISTA);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{idUnidade}", method = RequestMethod.DELETE)
	public ModelAndView unidadeExcluir(@PathVariable Long idUnidade) {
		ModelAndView mv = new ModelAndView(REDIRECT_UNIDADE_MEDIDA_LISTA);
		unidadeMedidaServices.excluir(idUnidade);
		return mv;
	}
	
	@ModelAttribute("todasUnidadesMedida")
	public List<UnidadeMedida> todasUnidadeMedida() {
		return unidadeMedidaRepository.findAll();
	}
}
