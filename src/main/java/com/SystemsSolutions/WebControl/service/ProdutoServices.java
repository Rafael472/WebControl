package com.SystemsSolutions.WebControl.service;

import com.SystemsSolutions.WebControl.model.Produto;
import com.SystemsSolutions.WebControl.repository.ProdutoRepository;
import com.SystemsSolutions.WebControl.repository.UnidadeMedidaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProdutoServices {

	@Autowired ProdutoRepository produtoRepository;
	@Autowired UnidadeMedidaRepository unidadeMedidaRepository;
	@Autowired UsuarioServices usuarioServices;
	
	public void deletar(Long codigo) {
		produtoRepository.deleteById(codigo);
	}

	public void salvar(Produto produto) {
		String usuario = usuarioServices.getUsuarioAutenticado();
		Date data = new Date(System.currentTimeMillis());
		
		produto.setDataCadastro(data);
		produto.setUsuarioCadastro(usuario);
		produto.setDataAlteracao(data);
		produto.setHoraAlteracao(data);
		produto.setUsuarioAlteracao(usuario);
		produtoRepository.save(produto);
	}
	
	public Produto buscaPorCodigo(String codigo) {
		return produtoRepository.findByCodigo(codigo);
	}

	public boolean produtoExiste(@Valid Produto produto) {
		return (buscaPorCodigo(produto.getCodigo()) != null);
	}

	public void editar(Produto produto) {
		String usuario = usuarioServices.getUsuarioAutenticado();
		Optional<Produto> produtoTemp = produtoRepository.findById(produto.getId_produto());
		Date data = new Date(System.currentTimeMillis());
		
		produto.setDataCadastro(produtoTemp.get().getDataCadastro());
		produto.setUsuarioCadastro(produtoTemp.get().getUsuarioCadastro());
		produto.setDataAlteracao(data);
		produto.setHoraAlteracao(data);
		produto.setUsuarioAlteracao(usuario);
		produtoRepository.save(produto);
	}
	
}
