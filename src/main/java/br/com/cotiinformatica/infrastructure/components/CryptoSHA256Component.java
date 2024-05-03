package br.com.cotiinformatica.infrastructure.components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class CryptoSHA256Component {

	public String encrypt(String value) {
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(value.getBytes());

			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException("Could not encrypt value with SHA-256", e);
		}
	}

	public static void main(String[] args) {
		CryptoSHA256Component crypto = new CryptoSHA256Component();
		String value = "YourStringToEncrypt";
		String encryptedValue = crypto.encrypt(value);

		System.out.println("Original Value: " + value);
		System.out.println("Encrypted Value: " + encryptedValue);
	}
}
