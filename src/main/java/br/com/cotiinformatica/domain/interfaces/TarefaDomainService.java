package br.com.cotiinformatica.domain.interfaces;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dto.ConsultarTarefaResponseDto;
import br.com.cotiinformatica.domain.dto.CriarTarefaRequestDto;
import br.com.cotiinformatica.domain.dto.EditarTarefaRequestDto;
public interface TarefaDomainService {
	
	ConsultarTarefaResponseDto criar(CriarTarefaRequestDto dto, UUID pessoaId);
	
	ConsultarTarefaResponseDto editar(EditarTarefaRequestDto dto, UUID pessoaId);
	
	ConsultarTarefaResponseDto excluir(UUID tarefaId, UUID pessoaId);
	
	List<ConsultarTarefaResponseDto> consultar(Instant dataMin, Instant dataMax, UUID pessoaId);
	
	ConsultarTarefaResponseDto obter(UUID tarefaId, UUID pessoaId);
}



