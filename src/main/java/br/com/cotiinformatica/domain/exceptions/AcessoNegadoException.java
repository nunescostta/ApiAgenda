package br.com.cotiinformatica.domain.exceptions;

public class AcessoNegadoException extends RuntimeException {

	public AcessoNegadoException() {
		super("Acesso Negado, usuario invalido");
	}

	
}
