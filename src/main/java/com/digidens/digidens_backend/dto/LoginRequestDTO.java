package com.digidens.digidens_backend.dto;

/**
 * DTO kirjautumispyynnölle. Käytetään vastaanottamaan käyttäjän syöttämä tunnus
 * ja salasana JSON-muodossa.
 */
public class LoginRequestDTO {

	private String username; // Käyttäjätunnus
	private String password; // Salasana

	/**
	 * Oletusrakentaja tarvitaan JSON-deserialisointia varten. Spring Boot käyttää
	 * tätä automaattisesti, kun JSON-data muunnetaan objektiksi.
	 */
	public LoginRequestDTO() {
	}

	// Getterit ja setterit
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}