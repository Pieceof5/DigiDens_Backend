package com.digidens.digidens_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.model.User;
import com.digidens.digidens_backend.repository.CourseInstanceRepository;
import com.digidens.digidens_backend.repository.UserRepository;

@Service
public class TeacherService {

	private final UserRepository userRepository;
	private final CourseInstanceRepository courseInstanceRepository;

	public TeacherService(UserRepository userRepository, CourseInstanceRepository courseInstanceRepository) {
		this.userRepository = userRepository;
		this.courseInstanceRepository = courseInstanceRepository;
	}

	/**
	 * Palauttaa kaikki toteutukset, joita opettaja opettaa
	 */
	public List<CourseInstance> getCourseInstancesByTeacher(Long teacherId) {
		Optional<User> teacherOpt = userRepository.findById(teacherId);
		if (teacherOpt.isEmpty() || !"TEACHER".equals(teacherOpt.get().getRole())) {
			throw new RuntimeException("Teacher not found or user is not a teacher: " + teacherId);
		}

		// Palauttaa kaikki toteutukset, joissa teacher = teacherId
		return courseInstanceRepository.findAllByTeacherId(teacherId);
	}
}
