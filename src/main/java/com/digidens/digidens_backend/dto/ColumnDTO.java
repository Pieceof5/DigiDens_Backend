package com.digidens.digidens_backend.dto;

import java.util.List;

/**
 * ColumnDTO edustaa sarakkeen tietoja TaskCardTemplateen liittyvissä DTO:issa.
 * 
 * Tämä DTO siirtää front-endin ja back-endin välillä sarakkeen määrittelyt.
 */
public class ColumnDTO {

	/**
	 * Sarakkeen tunniste. Frontend voi käyttää tätä sarakkeen yksilöimiseen.
	 */
	public Long columnId;

	/**
	 * Sarakkeen nimi, esim. "Tehtävä", "Pisteet" tms.
	 */
	public String label;

	/**
	 * Sarakkeen tyyppi: - TEXT: vapaamuotoinen tekstikenttä - MULTIPLE_CHOICE:
	 * monivalintavaihtoehto - CHECKBOX: valintaruutu
	 */
	public String type;

	/**
	 * Käyttörooli, joka määrittää kuka voi muokata saraketta: - STUDENT: opiskelija
	 * - TEACHER: opettaja
	 */
	public String role;

	/**
	 * Vaihtoehdot, joita käytetään MULTIPLE_CHOICE-sarakkeissa. Muussa tapauksessa
	 * null tai tyhjä lista.
	 */
	public List<String> options;
}
