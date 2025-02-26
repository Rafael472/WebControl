package com.SystemsSolutions.WebControl.security.config;

import com.SystemsSolutions.WebControl.service.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UsuarioServices service;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
						.requestMatchers("/usuario/**", "/perfis/**").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/produto/**").hasAnyAuthority("ROLE_ADMIN","ROLE_PRODUTO")
						.requestMatchers("/unidadeMedida/**").hasAnyAuthority("ROLE_ADMIN","ROLE_UNIDADE_MEDIDA")
						.requestMatchers("/vendas/faturamento/**").hasAnyAuthority("ROLE_ADMIN","ROLE_FATURAMENTO")
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.usernameParameter("usuario")
						.passwordParameter("senha")
						.defaultSuccessUrl("/home", true)
						.permitAll()
				)
				.logout(form -> form
						.logoutSuccessUrl("/login?logout")
				)
				.exceptionHandling(x -> x
						.accessDeniedPage("/acesso-negado"))
				//.csrf(AbstractHttpConfigurer::disable) // Habilite em produção!
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}