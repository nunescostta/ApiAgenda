package br.com.cotiinformatica.infrastructure.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.domain.dto.EmailDto;

@Component
public class EmailProducerComponent {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	//metodo para receber um objeto DTO contendo uma mensagem de email
	//e gravar esse objeto em uma fila do Rabbit
	public void sendMessage(EmailDto dto) throws Exception {
		
		//serializando os dados do dto para Json
		String data = objectMapper.writeValueAsString(dto);
		
		//gravando a mensagem na fila
		rabbitTemplate.convertAndSend(queue.getName(), data);
		
	}
	
}
