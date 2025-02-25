package com.SystemsSolutions.WebControl.controller;

import com.SystemsSolutions.WebControl.model.Faturamento;
import com.SystemsSolutions.WebControl.repository.FaturamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/faturamento")
public class FaturamentoController {

	@Autowired FaturamentoRepository faturamentoRepository;
	
	private static final String FATURAMENTO_LISTA = "FaturamentoLista";
	
	@RequestMapping({"","/"})
	public ModelAndView faturamento() {
		ModelAndView mv = new ModelAndView(FATURAMENTO_LISTA);
		return mv;
	}
	
	@ModelAttribute("todosFaturamentos")
	public List<Faturamento> todosFaturamentos() {
		return faturamentoRepository.findAll();
	}
}
