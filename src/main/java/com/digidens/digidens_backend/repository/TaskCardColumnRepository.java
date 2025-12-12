package com.digidens.digidens_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digidens.digidens_backend.model.TaskCardColumn;

/**
 * Repository-rajapinta TaskCardColumn-entiteetille.
 * 
 * Tämä mahdollistaa CRUD-toiminnot (luonti, haku, päivitys, poisto)
 * ilman erillistä toteutusluokkaa, koska JpaRepository
 * sisältää valmiiksi kaikki perustason tietokantatoiminnot.
 */
@Repository
public interface TaskCardColumnRepository extends JpaRepository<TaskCardColumn, Long> {

    // Tänne voidaan lisätä myöhemmin tarpeen mukaan omia kyselyitä,
    // esim. findByColumnName(String name).
}
