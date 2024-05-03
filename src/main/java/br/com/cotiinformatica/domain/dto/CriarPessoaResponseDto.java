package br.com.cotiinformatica.domain.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;


@Data
public class CriarPessoaResponseDto {

	private UUID id;

	private String nome;

	private String email;

	private Date dataHoraCadastro;
}
