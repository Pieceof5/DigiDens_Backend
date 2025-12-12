package com.digidens.digidens_backend.dto;

import java.util.List;

/**
 * TaskCardTemplateDTO siirtää mallipohjan tiedot front-endin ja back-endin
 * välillä. Tämä DTO sisältää sekä sarakkeet että rivit, joita käytetään
 * oppilaskohtaisten TaskCard-objektien luomiseen.
 */
public class TaskCardTemplateDTO {

	/**
	 * TaskCardin tunniste. Voidaan käyttää front-endissä tunnistamaan tietty
	 * kortti.
	 */
	public Long taskCardId;

	/**
	 * Mallipohjan nimi, esim. "Suoritekortti 2025".
	 */
	public String title;

	/**
	 * Lista sarakkeista, jotka kuuluvat mallipohjaan.
	 */
	public List<ColumnDTO> columns;

	/**
	 * Lista riveistä, jotka kuuluvat mallipohjaan. Jokaisella rivillä voi olla
	 * useita soluja (RowDTO sisältää solut).
	 */
	public List<RowDTO> rows;
}
