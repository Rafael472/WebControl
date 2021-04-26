package com.SystemsSolutions.WebControl.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.SystemsSolutions.WebControl.model.Produto;
import com.SystemsSolutions.WebControl.model.StatusProduto;
import com.SystemsSolutions.WebControl.model.UnidadeMedida;
import com.SystemsSolutions.WebControl.model.Usuario;
import com.SystemsSolutions.WebControl.repository.ProdutoRepository;
import com.SystemsSolutions.WebControl.repository.UnidadeMedidaRepository;
import com.SystemsSolutions.WebControl.service.ProdutoServices;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String PRODUTO_LISTA = "ProdutoLista";
	private static final String PRODUTO_ACAO = "ProdutoAcao";
	private static final String REDIRECT_PRODUTO_LISTA = "redirect:/produto";
	
	@Autowired ProdutoRepository produtoRepository;
	@Autowired UnidadeMedidaRepository unidadeMedidaRepository;
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
	
	@RequestMapping("/salvarProduto")
	public String salvar(@Valid Produto produto, Errors erros, RedirectAttributes attributes) {
		
		if(erros.hasErrors()) {
			Produto form = new Produto();
			BeanUtils.copyProperties(form, produto);
			return PRODUTO_ACAO;
		}
		produtoServices.salvar(produto);
		return PRODUTO_ACAO;
	}
	
	@RequestMapping(value="excluir/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo) {
		produtoServices.deletar(codigo);
		return REDIRECT_PRODUTO_LISTA;
	}
	
	@ModelAttribute("todasUnidadeMedida")
	public List<UnidadeMedida> todasUnidadeMedida() {
		return unidadeMedidaRepository.findAll();
	}
	
	@ModelAttribute("todosStatusProduto")
	public List<StatusProduto> todosStatusProduto() {
		return Arrays.asList(StatusProduto.values());
	}
}
