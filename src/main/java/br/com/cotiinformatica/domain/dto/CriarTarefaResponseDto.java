package br.com.cotiinformatica.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import br.com.cotiinformatica.domain.entities.Pessoa;
import lombok.Data;

@Data
public class CriarTarefaResponseDto {

	private UUID id;

	private LocalDate data;

	private LocalTime hora;
	
	private Pessoa pessoa;
	
}
