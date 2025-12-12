package com.digidens.digidens_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digidens.digidens_backend.model.User;
import com.digidens.digidens_backend.service.UserService;

import java.net.URI;
import java.util.List;

/**
 * REST-kontrolleri käyttäjien hallintaan. Tarjoaa endpointit käyttäjien hakuun,
 * luontiin, päivitykseen ja poistoon.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Hakee kaikki käyttäjät.
	 * 
	 * @return ResponseEntity sisältäen listan kaikista käyttäjistä
	 */
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	/**
	 * Hakee käyttäjän ID:n perusteella.
	 * 
	 * @param id haettavan käyttäjän ID
	 * @return ResponseEntity käyttäjän tiedoilla, tai 404 jos ei löydy
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Luo uuden käyttäjän.
	 * 
	 * @param newUser uusi käyttäjä JSON-bodyssä
	 * @return ResponseEntity luodulla käyttäjällä ja sijainti-headerilla
	 */
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User newUser) {
		User savedUser = userService.saveUser(newUser);
		return ResponseEntity.created(URI.create("/api/users/" + savedUser.getId())).body(savedUser);
	}

	/**
	 * Päivittää olemassa olevan käyttäjän tiedot.
	 * 
	 * @param id          päivitettävän käyttäjän ID
	 * @param userDetails uudet tiedot JSON-bodyssä
	 * @return ResponseEntity päivitetyllä käyttäjällä, tai 404 jos käyttäjää ei
	 *         löydy
	 */
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {

		return userService.getUserById(id).map(existingUser -> {
			existingUser.setUsername(userDetails.getUsername());
			existingUser.setPassword(userDetails.getPassword());
			existingUser.setRole(userDetails.getRole());
			existingUser.setFirstname(userDetails.getFirstname());
			existingUser.setLastname(userDetails.getLastname());
			existingUser.setGradeLevel(userDetails.getGradeLevel());
			existingUser.setStudentNumber(userDetails.getStudentNumber());
			return ResponseEntity.ok(userService.saveUser(existingUser));
		}).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Poistaa käyttäjän ID:n perusteella.
	 * 
	 * @param id poistettavan käyttäjän ID
	 * @return ResponseEntity ilman sisältöä, tai 404 jos käyttäjää ei löydy
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		if (!userService.getUserById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
