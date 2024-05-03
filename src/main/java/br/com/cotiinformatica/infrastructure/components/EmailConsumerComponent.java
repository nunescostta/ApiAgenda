package br.com.cotiinformatica.infrastructure.components;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.domain.dto.EmailDto;
import br.com.cotiinformatica.domain.entities.LogMensagem;
import br.com.cotiinformatica.infrastructure.repositories.LogMensagemRepository;

@Component
public class EmailConsumerComponent {

	@Autowired
	private EmailComponent emailComponent;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private LogMensagemRepository logMensagemRepository;
	
	//metodo para ficar processando conteudo da fila 
	@RabbitListener(queues = { "${queue.name}" })
	public void proccessMessage(@Payload String message) {
		
		LogMensagem logMensagem = new LogMensagem();
		logMensagem.setId(UUID.randomUUID());
		logMensagem.setMensagem(message);
		logMensagem.setDataHora(LocalTime.now());
		
		try {
			
			//deserializar o conteudo lido da fila
			EmailDto dto = objectMapper.readValue(message, EmailDto.class);
			
			//enviar email
			emailComponent.send(dto);
			
			//gravar um log de sucesso
			logMensagem.setStatus("Sucesso");
			
		} catch (Exception e) {
			logMensagem.setStatus("Erro");
			logMensagem.setErro(e.getMessage());
		}
		finally {
			//gravar o log no banco
			logMensagemRepository.save(logMensagem);
		}
		
	}
	
	
}
