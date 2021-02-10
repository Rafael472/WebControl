package com.SystemsSolutions.WebControl.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.SystemsSolutions.WebControl.service.UsuarioServices;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServices service;
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/images/**").permitAll()	//autoriza pastas css, js, images e seus respectivos arquivos
			.antMatchers("/", "/home").permitAll()	//autoriza pagina home
			.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.usernameParameter("usuario")
					.passwordParameter("senha")
					.defaultSuccessUrl("/", true)
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			.and().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}

	
}
