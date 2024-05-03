package br.com.cotiinformatica.domain.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dto.AutenticarPessoaRequestDto;
import br.com.cotiinformatica.domain.dto.AutenticarPessoaResponseDto;
import br.com.cotiinformatica.domain.dto.CriarPessoaRequestDto;
import br.com.cotiinformatica.domain.dto.CriarPessoaResponseDto;
import br.com.cotiinformatica.domain.dto.EmailDto;
import br.com.cotiinformatica.domain.entities.Pessoa;
import br.com.cotiinformatica.domain.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.domain.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.domain.interfaces.PessoaDomainService;
import br.com.cotiinformatica.infrastructure.components.CryptoSHA256Component;
import br.com.cotiinformatica.infrastructure.components.EmailProducerComponent;
import br.com.cotiinformatica.infrastructure.components.TokenComponent;
import br.com.cotiinformatica.infrastructure.repositories.PessoaRepository;

@Service
public class PessoaDomainServiceImpl implements PessoaDomainService {

	@Autowired
	private CryptoSHA256Component cryptoSHA256Component;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmailProducerComponent emailProducerComponent;
	
	@Autowired
	private TokenComponent tokenComponent;

	@Override
	public CriarPessoaResponseDto criar(CriarPessoaRequestDto dto) {
		if (pessoaRepository.findByEmail(dto.getEmail()) != null) {
			throw new EmailJaCadastradoException();
		}

		Pessoa pessoa = modelMapper.map(dto, Pessoa.class);
		pessoa.setId(UUID.randomUUID());
		pessoa.setSenha(cryptoSHA256Component.encrypt(dto.getSenha()));

		pessoaRepository.save(pessoa);

		enviarEmailDeBoasVindas(pessoa);

		CriarPessoaResponseDto response = modelMapper.map(pessoa, CriarPessoaResponseDto.class);
		response.setDataHoraCadastro(new Date());

		return response;
	}
	
	@Override
	public AutenticarPessoaResponseDto autenticar(AutenticarPessoaRequestDto dto) {

		Pessoa pessoa = pessoaRepository.findByEmailAndSenha
				(dto.getEmailAcesso(), cryptoSHA256Component.encrypt(dto.getSenhaAcesso()));

		if(pessoa == null) {
			throw new AcessoNegadoException();
		}
		
		AutenticarPessoaResponseDto response = modelMapper.map(pessoa, AutenticarPessoaResponseDto.class);
		response.setAcessToken(tokenComponent.generateToken(pessoa.getId()));
		
		return response;
	}

	
	//Método para escrever o email de boas vindas da pessoa
	private void enviarEmailDeBoasVindas(Pessoa pessoa) {

		String to = pessoa.getEmail();
		String subject = "Seja bem vindo ao sistema de Agenda - COTI Informática.";
		String body = "Olá, " + pessoa.getNome() + "\nSua conta foi criada com sucesso no sistema de Agenda de tarefas"
				+ "\nSeja bem vindo!" + "\n\nAtt, " + "\nEquipe COTI Informática";

		EmailDto dto = new EmailDto();
		dto.setDestinatario(to);
		dto.setAssunto(subject);
		dto.setMensagem(body);

		try {
			emailProducerComponent.sendMessage(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
