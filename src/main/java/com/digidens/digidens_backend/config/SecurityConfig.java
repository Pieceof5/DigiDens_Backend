package com.digidens.digidens_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig määrittelee Spring Security -asetukset. Tähän tiedostoon on
 * lisätty kaksi versiota: 1) Testausversio, jossa kaikki endpointit ovat
 * avoimia (ei autentikointia) 2) Kommentoitu tuotantoversio, jossa käytetään
 * käyttäjä- ja roolipohjaista autentikointia
 */
@Configuration
public class SecurityConfig {

	// ================================
	// Testausversio – kaikki endpointit avoimia
	// ================================
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // CSRF pois päältä testauksessa
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // kaikki endpointit avoimia
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ================================
	// Käyttäjä- ja roolipohjainen autentikointi // EI ENÄÄ PÄÄSYÄ SELAIMEN KAUTTA
	// REST APEIHIN
	// ================================
	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http .csrf(csrf -> csrf.disable())
	 * .authorizeHttpRequests(auth -> auth .requestMatchers(HttpMethod.OPTIONS,
	 * "/**").permitAll() // salli preflight
	 * .requestMatchers("/api/login").permitAll()
	 * .requestMatchers("/api/admin/**").hasRole("ADMIN")
	 * .requestMatchers("/api/teacher/**").hasRole("TEACHER")
	 * .requestMatchers("/api/student/**").hasRole("STUDENT")
	 * .anyRequest().authenticated() ) .cors(cors ->
	 * cors.configurationSource(corsConfigurationSource())); // käytä
	 * CorsConfigurationSource-beania return http.build(); }
	 * 
	 * @Bean public AuthenticationManager
	 * authenticationManager(AuthenticationConfiguration configuration) throws
	 * Exception { return configuration.getAuthenticationManager(); }
	 * 
	 * @Bean public CorsConfigurationSource corsConfigurationSource() {
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(List.of("http://localhost:5173")); // React
	 * frontend
	 * configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"
	 * )); configuration.setAllowedHeaders(List.of("*"));
	 * configuration.setAllowCredentials(true);
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration); return source; }
	 */
}
