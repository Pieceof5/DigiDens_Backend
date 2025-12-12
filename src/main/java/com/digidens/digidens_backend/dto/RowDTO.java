package com.digidens.digidens_backend.dto;

import java.util.Map;

/**
 * RowDTO edustaa rivin eli tehtävän tietoja TaskCardTemplateDTO:ssa.
 * 
 * Jokaisella rivillä voi olla useita soluja (cells), jotka yhdistetään
 * sarakkeisiin avain-arvo -parilla: - key: sarakkeen nimi tai tunniste - value:
 * solun arvo
 * 
 * Tämä DTO on tarkoitettu siirtämään tietoja front-endin ja back-endin välillä.
 */
public class RowDTO {

	/**
	 * Rivin tunniste.
	 */
	public Long rowId;

	/**
	 * Riviin kuuluvat solut. Avain = sarakkeen tunniste (tai nimi), arvo = solun
	 * sisältö.
	 */
	public Map<String, String> cells;
}