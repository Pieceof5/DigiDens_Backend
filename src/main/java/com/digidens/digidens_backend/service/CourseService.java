package com.digidens.digidens_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digidens.digidens_backend.model.Course;
import com.digidens.digidens_backend.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service-kerros sisältää logiikan, miten Coursea käsitellään. Kaikki
 * CRUD-operaatiot kulkevat Service-kerroksen kautta.
 */
@Service
public class CourseService {

	private final CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	// Tallentaa uuden kurssin tai päivittää olemassa olevan
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	// Hakee kaikki kurssit tietokannasta
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	// Hakee tietyn kurssin id:n perusteella
	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id);
	}

	// Poistaa kurssin id:n perusteella
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}
}
