package com.SystemsSolutions.WebControl.service;

import com.SystemsSolutions.WebControl.model.UnidadeMedida;
import com.SystemsSolutions.WebControl.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadeMedidaServices {

	@Autowired UnidadeMedidaRepository unidadeMedidaRepository;
	
	public boolean unidadeMedidaExiste(UnidadeMedida unidadeMedida) {
		if(unidadeMedidaRepository.findBySigla(unidadeMedida.getSigla()) != null)
			return true;
		if(unidadeMedidaRepository.findByDescricao(unidadeMedida.getDescricao()) != null)
			return true;
		
		return false;
	}

	public void salvar(UnidadeMedida unidadeMedida) {
		unidadeMedidaRepository.save(unidadeMedida);
	}

	public void excluir(Long idUnidade) {
		unidadeMedidaRepository.deleteById(idUnidade);
	}

}
