package com.digidens.digidens_backend.repository;

import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {

	/**
	 * Hakee kaikki kurssitoteutukset tietyn kurssin perusteella
	 * 
	 * @param courseId haettavan kurssin ID
	 * @return lista kurssitoteutuksista
	 */
	List<CourseInstance> findAllByCourseId(Long courseId);

	/**
	 * Hakee kaikki kurssitoteutukset tietyn opettajan perusteella
	 * 
	 * @param teacherId opettajan ID
	 * @return lista kurssitoteutuksista, joita opettaja opettaa
	 */
	List<CourseInstance> findAllByTeacherId(Long teacherId);

	/**
	 * Hakee kaikki kurssitoteutukset, joissa tietty opiskelija on mukana
	 * 
	 * @param student opiskelija
	 * @return lista kurssitoteutuksista, joissa opiskelija on ilmoittautunut
	 */
	List<CourseInstance> findAllByStudentsContaining(User student);
}
