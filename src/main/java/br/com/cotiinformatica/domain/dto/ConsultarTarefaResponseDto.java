package br.com.cotiinformatica.domain.dto;
import java.util.UUID;
import lombok.Data;
@Data
public class ConsultarTarefaResponseDto {
	private UUID id;
	private String nome;
	private String data;
	private String hora;
	private Integer prioridade;
}
