package com.SystemsSolutions.WebControl.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/images/**").permitAll()	//autoriza pastas css, js, images e seus respectivos arquivos
			.antMatchers("/", "home").permitAll()	//autoriza pagina home
			.antMatchers("/login?logout", "/login?error").permitAll()
			.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					//.usernameParameter("usuario")
					//.passwordParameter("senha")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			.and().httpBasic();
	}

}
