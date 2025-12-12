package com.digidens.digidens_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig määrittelee CORS-säännöt sovellukselle.
 * 
 * Huom! Tällä hetkellä asetukset sallivat kaiken liikenteen localhost:5173
 * -osoitteesta, mikä on tarkoitettu React-kehityspalvelimelle. Tuotannossa tämä
 * URL pitää vaihtaa oikeaan frontend-palvelimen osoitteeseen.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // kaikki REST-endpointit
				.allowedOrigins("http://localhost:5173") // kehitysympäristön frontend
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // sallitut HTTP-menetelmät
				.allowedHeaders("*") // sallitut headerit
				.allowCredentials(true); // sallii evästeet ja autentikointitunnisteet
	}
}
