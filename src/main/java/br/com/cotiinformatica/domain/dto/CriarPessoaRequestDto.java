package br.com.cotiinformatica.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CriarPessoaRequestDto {

	@Size(min =8, max=100, message = "Por favor, informe um nome com 8 a 100 caracteres")
	@NotBlank(message = "Por favor, informe um nome com 8 a 100 caracteres")
	private String nome;
	
	@Email(message = "Por favor, informe um endereço de email valido")
	@NotBlank(message = "Por favor, informe o email do usuario")
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Por favor, informe a senha com letras maiusculas, minusculas, numeros, simbolos e pelo menos 8 caracteres")
	@NotBlank(message ="Por favor, informe a senha do usuario")
	private String senha;
}
