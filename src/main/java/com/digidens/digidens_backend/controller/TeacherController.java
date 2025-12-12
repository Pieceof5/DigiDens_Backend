package com.digidens.digidens_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digidens.digidens_backend.dto.TeacherCourseInstancesDTO;
import com.digidens.digidens_backend.model.User;
import com.digidens.digidens_backend.repository.UserRepository;
import com.digidens.digidens_backend.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	private final TeacherService teacherService;
	private final UserRepository userRepository;

	public TeacherController(TeacherService teacherService, UserRepository userRepository) {
		this.teacherService = teacherService;
		this.userRepository = userRepository;
	}

	/**
	 * Palauttaa kaikki kurssitoteutukset, joita opettaja opettaa DTO:n muodossa
	 */
	@GetMapping("/{id}/course-instances")
	public TeacherCourseInstancesDTO getTeacherCourseInstances(@PathVariable Long id) {
		// Hae opettaja
		User teacher = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found: " + id));

		if (!"TEACHER".equals(teacher.getRole())) {
			throw new RuntimeException("User is not a teacher: " + id);
		}

		// Hae kurssitoteutukset opettajalle
		List<com.digidens.digidens_backend.model.CourseInstance> instances = teacherService
				.getCourseInstancesByTeacher(id);

		// Muunna CourseInstance → CourseInstanceDTO
		List<TeacherCourseInstancesDTO.CourseInstanceDTO> instanceDTOs = instances.stream()
				.map(TeacherCourseInstancesDTO::fromEntity) // helper-metodi huolehtii myös StudentDTO-muunnoksesta
				.collect(Collectors.toList());

		// Luo DTO opettajalle
		TeacherCourseInstancesDTO dto = new TeacherCourseInstancesDTO();
		dto.setTeacherId(teacher.getId());
		dto.setFirstname(teacher.getFirstname());
		dto.setLastname(teacher.getLastname());
		dto.setCourseInstances(instanceDTOs);

		return dto;
	}
}
