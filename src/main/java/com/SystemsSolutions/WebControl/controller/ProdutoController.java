package com.SystemsSolutions.WebControl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SystemsSolutions.WebControl.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String PRODUTO_LISTA = "ProdutoLista";
	
	@Autowired ProdutoRepository produtoRepository;
	
	@RequestMapping({"","/"})
	public ModelAndView usuario() {
		ModelAndView mv = new ModelAndView(PRODUTO_LISTA);
		mv.addObject("todosProdutos", produtoRepository.findAll());
		return mv;
	}
}
