package br.com.cotiinformatica.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	//ler nome da fila que iremos acessar no servidor
	@Value("${queue.name}")
	private String queueName;
	
	
	//metodo para conectar o projeto na fila do servidor
	@Bean
	public Queue queue () {
		return new Queue(queueName, true);
	}
	
	
}
