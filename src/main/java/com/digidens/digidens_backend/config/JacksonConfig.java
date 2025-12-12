package com.digidens.digidens_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tools.jackson.databind.ObjectMapper;

/**
 * JacksonConfig määrittelee ObjectMapper-beanin Springin kontekstiin.
 * ObjectMapper on vastuussa JSON-objektien sarjoittamisesta ja
 * desarjoittamisesta. Tätä käytetään mm. REST-rajapinnoissa JSONin käsittelyyn.
 */
@Configuration
public class JacksonConfig {

	/**
	 * Luo ja palauttaa ObjectMapperin. Beanin avulla sama ObjectMapper voidaan
	 * injektoida eri luokkiin.
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
