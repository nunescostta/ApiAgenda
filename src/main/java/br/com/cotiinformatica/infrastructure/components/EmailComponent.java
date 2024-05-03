package br.com.cotiinformatica.infrastructure.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.domain.dto.EmailDto;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailComponent {

	//componente do spring email para enviar email usando as configurações do propriets
	@Autowired
	private JavaMailSender javaMailSender;
	
	//lendo o valor da congiguracao contida no propriets
	@Value("${spring.mail.username}")
	private String userName;
	
	//metodo para fazer envio do email
	public void send (EmailDto dto) throws Exception {
		
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			
			helper.setFrom(userName); //remetente
			helper.setTo(dto.getDestinatario()); //destinatatio
			helper.setSubject(dto.getAssunto()); //assunto
			helper.setText(dto.getMensagem()); //texto
			
			javaMailSender.send(mimeMessage);
		
	}
	
	
	
	
	
	
}
