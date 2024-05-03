package br.com.cotiinformatica.domain.dto;

import lombok.Data;

@Data
public class EmailDto {

	private String destinatario;
	
	private String assunto;
	
	private String mensagem;
	
}
