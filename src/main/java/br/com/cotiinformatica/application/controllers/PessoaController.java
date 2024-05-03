package br.com.cotiinformatica.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dto.AutenticarPessoaRequestDto;
import br.com.cotiinformatica.domain.dto.AutenticarPessoaResponseDto;
import br.com.cotiinformatica.domain.dto.CriarPessoaRequestDto;
import br.com.cotiinformatica.domain.dto.CriarPessoaResponseDto;
import br.com.cotiinformatica.domain.interfaces.PessoaDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {

	@Autowired
	private PessoaDomainService pessoaDomainService;


	@PostMapping("criar")
	public ResponseEntity<CriarPessoaResponseDto> criar(@RequestBody @Valid CriarPessoaRequestDto dto) {

		CriarPessoaResponseDto response = pessoaDomainService.criar(dto);

		return ResponseEntity.status(201).body(response);
	}

	@PostMapping("autenticar")
	public ResponseEntity<AutenticarPessoaResponseDto> autenticar(@RequestBody @Valid AutenticarPessoaRequestDto dto){
		
		AutenticarPessoaResponseDto response = pessoaDomainService.autenticar(dto);
		
		return ResponseEntity.status(200).body(response);
	}
}
