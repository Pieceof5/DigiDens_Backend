package com.digidens.digidens_backend.mapper;

import java.util.stream.Collectors;

import com.digidens.digidens_backend.dto.TaskCardTemplateDTO;
import com.digidens.digidens_backend.dto.ColumnDTO;
import com.digidens.digidens_backend.dto.RowDTO;
import com.digidens.digidens_backend.model.TaskCardTemplate;
import com.digidens.digidens_backend.model.TaskCardCell;

/**
 * TaskCardTemplateMapper
 *
 * Tämä luokka muuntaa TaskCardTemplate-entityn DTO-muotoon.
 * DTO (Data Transfer Object) on kevyt olio, jota käytetään tietojen
 * siirtämiseen backendistä frontendiin ilman, että alkuperäiset entityt
 * paljastetaan suoraan.
 */
public class TaskCardTemplateMapper {

    /**
     * Muuntaa TaskCardTemplate-entityn TaskCardTemplateDTO:ksi.
     *
     * @param template TaskCardTemplate entity
     * @return TaskCardTemplateDTO
     */
    public static TaskCardTemplateDTO toDTO(TaskCardTemplate template) {
        TaskCardTemplateDTO dto = new TaskCardTemplateDTO();
        dto.taskCardId = template.getId();       // Template ID
        dto.title = template.getName();          // Template nimi

        // --- Sarakkeet → DTO ---
        dto.columns = template.getColumns().stream()
                .map(col -> {
                    ColumnDTO c = new ColumnDTO();
                    c.columnId = col.getId();                // Sarakkeen ID
                    c.label = col.getName();                 // Sarakkeen nimi
                    c.type = col.getType();                  // Sarakkeen tyyppi (TEXT, CHECKBOX, MULTIPLE_CHOICE)
                    c.role = col.getRole();                  // Käyttäjärooli, joka täyttää sarakkeen (TEACHER/STUDENT)
                    c.options = col.getOptions() != null ? col.getOptions() : java.util.List.of(); // Valinnat monivalinta-sarakkeille
                    return c;
                })
                .collect(Collectors.toList());

        // --- Rivit ja solut → DTO ---
        dto.rows = template.getRows().stream()
                .map(row -> {
                    RowDTO r = new RowDTO();
                    r.rowId = row.getId(); // Rivin ID

                    // Mapataan solut: columnName → cellValue
                    r.cells = row.getCells().stream()
                            .collect(Collectors.toMap(
                                    TaskCardCell::getColumnName,
                                    TaskCardCell::getCellValue
                            ));
                    return r;
                })
                .collect(Collectors.toList());

        return dto;
    }
}
