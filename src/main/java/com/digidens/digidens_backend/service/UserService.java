package com.digidens.digidens_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digidens.digidens_backend.model.User;
import com.digidens.digidens_backend.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
