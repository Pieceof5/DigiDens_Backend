package com.digidens.digidens_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digidens.digidens_backend.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	/**
	 * Hakee kaikki kurssit tietyn vuosiluokan perusteella
	 * 
	 * @param gradeLevel vuosiluokka
	 * @return lista kursseista
	 */
	List<Course> findByGradeLevel(String gradeLevel);
}