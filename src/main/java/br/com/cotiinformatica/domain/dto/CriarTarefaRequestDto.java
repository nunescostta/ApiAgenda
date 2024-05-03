package br.com.cotiinformatica.domain.dto;

import br.com.cotiinformatica.domain.entities.Pessoa;
import lombok.Data;

@Data
public class CriarTarefaRequestDto {

	private String nome;

	private Integer prioridade;
	
	private Pessoa pessoa;

}
