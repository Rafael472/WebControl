package com.SystemsSolutions.WebControl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.SystemsSolutions.WebControl.repository.ProdutoRepository;
import com.SystemsSolutions.WebControl.repository.UnidadeMedidaRepository;
import com.SystemsSolutions.WebControl.service.ProdutoServices;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String PRODUTO_LISTA = "ProdutoLista";
	private static final String PRODUTO_ACAO = "ProdutoAcao";
	private static final String REDIRECT_PRODUTO_LISTA = "redirect:/produto";
	private static final String REDIRECT_PRODUTO_NOVO = "redirect:/produto/novo";
	
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
	
	@RequestMapping(value = "/salvarProduto", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Produto produto, Errors erros, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(PRODUTO_ACAO);
		if(erros.hasErrors()) {
			return mv;
		}
		
		if(produtoServices.produtoExiste(produto)) {
			mv.addObject("classe", "alert alert-warning");
			mv.addObject("mensagem", "Código "+ produto.getCodigo() +" já existe!");
			return mv;
		}
		
		try {
			produtoServices.salvar(produto);
			attributes.addFlashAttribute("classe", "alert alert-success");
			attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
		}catch (Exception ex) {
			attributes.addFlashAttribute("classe", "alert alert-danger");
			attributes.addFlashAttribute("mensagem", ex.getMessage());
		}
		mv.setViewName(REDIRECT_PRODUTO_NOVO);
		return mv;
	}
	
	@RequestMapping(value="excluir/{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo) {
		produtoServices.deletar(codigo);
		return REDIRECT_PRODUTO_LISTA;
	}
	
	@RequestMapping(value="/{idProduto}")
	public ModelAndView editar(@PathVariable Long idProduto) {
		ModelAndView mv = new ModelAndView(PRODUTO_ACAO);
		Optional<Produto> produto = produtoRepository.findById(idProduto);
		
		mv.addObject("action", "editar");
		mv.addObject(produto.get());
		return mv;
	}
	
	@RequestMapping(value="editar/{codigo}", method = RequestMethod.PUT)
	public ModelAndView editarPruduto(@Validated Produto produto, Errors erros, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(PRODUTO_ACAO);
		if(erros.hasErrors()) {
			mv.addObject(produto);
			mv.addObject("action", "editar");
			return mv;
		}
		produtoServices.editar(produto);
		mv.setViewName(REDIRECT_PRODUTO_LISTA);
		return mv;
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
