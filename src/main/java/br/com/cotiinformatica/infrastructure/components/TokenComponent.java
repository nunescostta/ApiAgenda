package br.com.cotiinformatica.infrastructure.components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenComponent {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private String jwtExpiration;

	// metodo pra gerar token jwt

	public String generateToken(UUID usuarioId) {

		// gerando data atual
		Date dataAtual = new Date();

		// data Expiraçao
		Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(jwtExpiration));

		// gerar e retornar o token
		return Jwts.builder().setSubject(usuarioId.toString()) // id do usuario autenticado
				.setNotBefore(dataAtual) // data ge geração token
				.setExpiration(dataExpiracao) // data expiração token
				.signWith(SignatureAlgorithm.HS256, jwtSecret) // assinatura antifalsificação
				.compact(); // finaliza e trtorna o token
	}

}