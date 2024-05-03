package br.com.cotiinformatica.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //todos os endpoints da API
			.allowedOrigins("*") //permissão
			.allowedMethods("POST", "PUT", "DELETE", "GET") //metodos permitidos)
			.allowedHeaders("*"); //parametros de cabeçalho das requisições
		
	}
	
	
}