package br.com.cotiinformatica.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticarPessoaRequestDto {
	
	@Email(message = "Por favor, informe um endereço de email valido.")
	@NotEmpty(message = "Por favor, informe um email valido.")
	private String emailAcesso;
	
	
	@Size(min=8, message = "Por favor, informe no minimo 8 caracteres.")
	@NotEmpty(message = "Por favor, informe sua senha de acesso.")
	private String senhaAcesso;
}
