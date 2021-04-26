package com.SystemsSolutions.WebControl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SystemsSolutions.WebControl.repository.ProdutoRepository;

@Service
public class ProdutoServices {

	@Autowired ProdutoRepository produtoRepository;

	public void deletar(Long codigo) {
		produtoRepository.deleteById(codigo);
	}
	
}
