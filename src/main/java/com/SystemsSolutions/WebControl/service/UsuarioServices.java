package com.SystemsSolutions.WebControl.service;

import com.SystemsSolutions.WebControl.model.Perfil;
import com.SystemsSolutions.WebControl.model.Usuario;
import com.SystemsSolutions.WebControl.repository.PerfilRepository;
import com.SystemsSolutions.WebControl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PerfilRepository perfilRepository;
	
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
		if(lista.isPresent())
			return lista.get();
		return null;
	}
	
	@Transactional()
	public void editarPorUsuario(Usuario usuario, Long id) {
		Date data = new Date(System.currentTimeMillis());
		usuario.setId_usuario(id);
		usuario.setDataAlteracao(data); //GET DATA
		usuario.setHoraAlteracao(data); //GET HORA
		if(!usuario.getSenha().equals("")) { //Se senha não for vazia, salva usuario com a nova senha
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			repository.EditByUsuario(usuario.getUsername(), usuario.getSenha(), usuario.getEmail(), usuario.getDataAlteracao(), usuario.getHoraAlteracao(), usuario.getStatus(), id);
		}else //senão, salva usuário sem alterar a senha
			repository.EditByUsuario(usuario.getUsername(), usuario.getEmail(), usuario.getDataAlteracao(), usuario.getHoraAlteracao(), usuario.getStatus(), id);
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
		
		if(usuario.getStatus().toString().equals("BLOQUEADO") || usuario.getStatus().toString().equals("PENDENTE")) {
			usuario = null;
		}
		
		return User.builder()
				.username(usuario.getUsername())
				.password(usuario.getSenha())
				.roles(getAuthorities(usuario.getPerfis()))
				.build();
	}

	//converte lista de perfis para array string
	private String[] getAuthorities(List<Perfil> perfis) {
		String[] authorities = new String[perfis.size()];
		
		for (int i = 0; i < perfis.size(); i++) {
			authorities[i] = perfis.get(i).getDesc();
		}
		return authorities;
	}
	
	public String getUsuarioAutenticado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String usuario;

		if (principal instanceof UserDetails) {
			usuario = ((UserDetails)principal).getUsername();
		} else {
			usuario = principal.toString();
		}
		return usuario;
	}
	
	//checa se já existe usuário no banco de dados
	public Boolean usuarioExiste(Usuario usuario) {
		return (buscarPorUsuario(usuario.getUsername()) != null);
	}

	public void inserirRelacao(Long idUsuario, Long idPerfil) {
		perfilRepository.inserir(idUsuario, idPerfil);
	}

	public void deletarRelacao(Long idUsuario, Long idPerfil) {
		perfilRepository.deletar(idUsuario, idPerfil);
	}
}
