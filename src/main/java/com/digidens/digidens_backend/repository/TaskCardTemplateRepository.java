package com.digidens.digidens_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.model.TaskCardTemplate;

/**
 * Repository-rajapinta TaskCardTemplate-entiteetille.
 * 
 * Tarjoaa CRUD-toiminnot TaskCardTemplate-taululle sekä lisähaun, jolla voidaan
 * hakea kaikki mallipohjat tietylle kurssitoteutukselle.
 */
@Repository
public interface TaskCardTemplateRepository extends JpaRepository<TaskCardTemplate, Long> {

	/**
	 * Hakee kaikki TaskCardTemplate-objektit, jotka kuuluvat annettuun
	 * CourseInstanceen.
	 *
	 * @param courseInstance kurssitoteutus, jonka mallipohjat haetaan
	 * @return lista TaskCardTemplate-objekteista
	 */
	List<TaskCardTemplate> findAllByCourseInstance(CourseInstance courseInstance);
}
