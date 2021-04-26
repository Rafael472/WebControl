package com.SystemsSolutions.WebControl.service;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SystemsSolutions.WebControl.model.Produto;
import com.SystemsSolutions.WebControl.repository.ProdutoRepository;
import com.SystemsSolutions.WebControl.repository.UnidadeMedidaRepository;

@Service
public class ProdutoServices {

	@Autowired ProdutoRepository produtoRepository;
	@Autowired UnidadeMedidaRepository unidadeMedidaRepository;
	
	public void deletar(Long codigo) {
		produtoRepository.deleteById(codigo);
	}

	public void salvar(Produto produto) {
		Date data = new Date(System.currentTimeMillis());
		produto.setDataCadastro(data);
		produto.setDataAlteracao(data);
		produto.setUsuarioCadastro("IMPLEMENTAR");
		produto.setUsuarioAlteracao("IMPLEMENTAR");
		produtoRepository.save(produto);
	}
	
}
