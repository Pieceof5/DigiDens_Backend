package com.digidens.digidens_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digidens.digidens_backend.model.TaskCardRow;

/**
 * Repository-rajapinta TaskCardRow-entiteetille.
 * 
 * JpaRepository tarjoaa valmiit CRUD-toiminnot (luonti, haku, päivitys, poisto)
 * TaskCardRow-taululle.
 */
@Repository
public interface TaskCardRowRepository extends JpaRepository<TaskCardRow, Long> {
}
