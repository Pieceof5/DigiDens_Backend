package com.digidens.digidens_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digidens.digidens_backend.model.TaskCard;

/**
 * Repository-rajapinta TaskCard-entiteetille.
 * 
 * JpaRepository tarjoaa valmiit CRUD-toiminnot (luonti, haku, päivitys, poisto)
 * TaskCard-taululle.
 * 
 * Lisäksi rajapinta sisältää omat kyselymetodit, kuten: - findByStudentId(Long
 * studentId): hakee kaikki TaskCardit tietylle opiskelijalle
 */
@Repository
public interface TaskCardRepository extends JpaRepository<TaskCard, Long> {
	List<TaskCard> findByStudentId(Long studentId);
}
