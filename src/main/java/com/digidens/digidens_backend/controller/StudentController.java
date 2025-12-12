package com.digidens.digidens_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digidens.digidens_backend.dto.StudentCourseProgressDTO;
import com.digidens.digidens_backend.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * Hakee opiskelijan kurssitoteutukset ja niihin liittyvän edistymisen DTO:n
	 * muodossa. URL frontendille: GET /api/students/{id}/courses
	 */
	@GetMapping("/{id}/courses")
	public List<StudentCourseProgressDTO> getCoursesWithProgress(@PathVariable Long id) {
		return studentService.getCoursesWithProgressByStudentId(id);
	}
}
