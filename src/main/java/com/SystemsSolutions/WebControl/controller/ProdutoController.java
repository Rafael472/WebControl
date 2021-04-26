package com.SystemsSolutions.WebControl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SystemsSolutions.WebControl.model.Produto;
import com.SystemsSolutions.WebControl.repository.ProdutoRepository;
import com.SystemsSolutions.WebControl.service.ProdutoServices;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String PRODUTO_LISTA = "ProdutoLista";
	private static final String PRODUTO_ACAO = "ProdutoAcaoModificado";
	private static final String REDIRECT_PRODUTO_LISTA = "redirect:/produto";
	
	@Autowired ProdutoRepository produtoRepository;
	@Autowired ProdutoServices produtoServices;
	
	@RequestMapping({"","/"})
	public ModelAndView produto() {
		ModelAndView mv = new ModelAndView(PRODUTO_LISTA);
		mv.addObject("todosProdutos", produtoRepository.findAll());
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(PRODUTO_ACAO);
		mv.addObject(new Produto());
		return mv;
	}
	
	@RequestMapping(value="excluir/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo) {
		produtoServices.deletar(codigo);
		return REDIRECT_PRODUTO_LISTA;
	}
}
