package com.digidens.digidens_backend.repository;

import com.digidens.digidens_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Hakee käyttäjän käyttäjätunnuksen perusteella.
	 * @param username haettavan käyttäjän tunnus
	 * @return Optional sisältää käyttäjän jos löytyi, muuten tyhjä
	 */
	Optional<User> findByUsername(String username);
}
