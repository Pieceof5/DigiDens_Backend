package com.digidens.digidens_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.digidens.digidens_backend.dto.LoginRequestDTO;
import com.digidens.digidens_backend.model.User;
import com.digidens.digidens_backend.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // frontendin osoite
public class AuthController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
		User user = userService.getUserByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("Käyttäjää ei löydy"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Väärä salasana");
		}

		return ResponseEntity.ok(user);
	}
}