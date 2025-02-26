package com.SystemsSolutions.WebControl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class WebControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebControlApplication.class, args);

		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//var f = encoder.encode("ADMIN");
		//var f = encoder.matches("ADMIN", "$2a$10$ldbPqWR9SgC/G4.NPoIBVe1kVmfXS.lcWavZN3eIyysdbAN1hxAWi");
		//System.out.println(f);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public FlywayConfigurationCustomizer flywayConfigurationCustomizer() {
		return configuration -> configuration.baselineOnMigrate(true);
	}

}
