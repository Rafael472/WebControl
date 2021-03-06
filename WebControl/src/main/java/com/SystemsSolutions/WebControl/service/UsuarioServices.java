package com.SystemsSolutions.WebControl.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SystemsSolutions.WebControl.model.Perfil;
import com.SystemsSolutions.WebControl.model.Usuario;
import com.SystemsSolutions.WebControl.repository.UsuarioRepository;

@Service
public class UsuarioServices implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	//salva novo usuário
	public void salvarUsuario(Usuario usuario) {
		try {
			Date data = new Date(System.currentTimeMillis());
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuario.setDataAlteracao(data);	//GET DATA
			usuario.setHoraAlteracao(data); //GET HORA
			repository.save(usuario);
		} catch (DataIntegrityViolationException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
		
	}
	
	public Usuario buscaPorId(Long id) {
		Optional<Usuario> lista = repository.findById(id);
		if(!lista.isEmpty())
			return lista.get();
		return null;
	}
	
	@Transactional()
	public void editarPorUsuario(Usuario usuario, Long id) {
		Date data = new Date(System.currentTimeMillis());
		usuario.setId_Usuario(id);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setDataAlteracao(data); //GET DATA
		usuario.setHoraAlteracao(data); //GET HORA
		repository.EditByUsuario(
				usuario.getUsuario()
				, usuario.getSenha()
				, usuario.getEmail()
				, usuario.getDataAlteracao()
				, usuario.getHoraAlteracao()
				, usuario.getDataCadastro()
				, usuario.getStatus()
				, id);
	}
	
	//busca usuário por usuário no banco de dados
	@Transactional(readOnly = true)
	public Usuario buscarPorUsuario(String usuario) {
		return repository.findByUsuario(usuario);
	}
	
	//busca usuário por email no banco de dados
	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}
	
	//busca todos os usuários
	public List<Usuario> buscarTodos(){
		return repository.findAll();
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	//autenticação de usuário (spring-security)
	@Override @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		Usuario usuario = buscarPorUsuario(user);
		System.out.println("Usuário encontrado: " + usuario.getUsuario()
						+  "\nSenha: " + usuario.getSenha()
						+  "\nStatus: " + usuario.getStatus());
		
		if(usuario.getStatus().toString().equals("BLOQUEADO") || usuario.getStatus().toString().equals("PENDENTE")) {
			usuario = null;
		}
		
		return new User(
				usuario.getUsuario(),
				usuario.getSenha(),
				AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
		);
	}
	//converte lista de perfis para array string
	private String[] getAuthorities(List<Perfil> perfis) {
		String[] authorities = new String[perfis.size()];
		
		for (int i = 0; i < perfis.size(); i++) {
			authorities[i] = perfis.get(i).getDesc();
		}
		return authorities;
	}
	
	//checa se já existe usuário no banco de dados
	public Boolean usuarioExiste(Usuario usuario) {
		return (buscarPorUsuario(usuario.getUsuario()) != null);
	}
}
