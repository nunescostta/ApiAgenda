package br.com.cotiinformatica.domain.interfaces;

import br.com.cotiinformatica.domain.dto.AutenticarPessoaRequestDto;
import br.com.cotiinformatica.domain.dto.AutenticarPessoaResponseDto;
import br.com.cotiinformatica.domain.dto.CriarPessoaRequestDto;
import br.com.cotiinformatica.domain.dto.CriarPessoaResponseDto;

public interface PessoaDomainService {

	//metodo para criar pessoa
	CriarPessoaResponseDto criar (CriarPessoaRequestDto dto);


	AutenticarPessoaResponseDto autenticar(AutenticarPessoaRequestDto dto);
}
