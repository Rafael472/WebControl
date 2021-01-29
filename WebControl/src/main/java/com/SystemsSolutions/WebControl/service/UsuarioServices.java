package com.SystemsSolutions.WebControl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SystemsSolutions.WebControl.model.Perfil;
import com.SystemsSolutions.WebControl.model.Usuario;
import com.SystemsSolutions.WebControl.repository.UsuarioRepository;

@Service
public class UsuarioServices implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	public void salvarUsuario(Usuario usuario) {
		try {
			repository.save(usuario);
		} catch (DataIntegrityViolationException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
		
	}
	
	@Transactional(readOnly = true)
	public Usuario buscarPorUsuario(String usuario) {
		return repository.findByUsuario(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		Usuario usuario = buscarPorUsuario(user);
		return new User(
				usuario.getUsuario(),
				usuario.getSenha(),
				AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
				);
	}
	
	private String[] getAuthorities(List<Perfil> perfis) {
		String[] authorities = new String[perfis.size()];
		
		for (int i = 0; i < perfis.size(); i++) {
			authorities[i] = perfis.get(i).getDesc();
		}
		return authorities;
	}
}
