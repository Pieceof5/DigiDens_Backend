package com.digidens.digidens_backend.repository;

import com.digidens.digidens_backend.model.TaskCardCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository-rajapinta TaskCardCell-entiteetille.
 * 
 * JpaRepository tarjoaa valmiit CRUD-toiminnot (luonti, haku, päivitys, poisto) 
 * TaskCardCell-taululle, joten erillistä toteutusluokkaa ei tarvita.
 * 
 * Mahdollistaa myös omien kyselymetodien lisäämisen tulevaisuudessa.
 */
@Repository
public interface TaskCardCellRepository extends JpaRepository<TaskCardCell, Long> {
    // Esim. voidaan lisätä findByRowId(Long rowId) tarpeen mukaan
}
