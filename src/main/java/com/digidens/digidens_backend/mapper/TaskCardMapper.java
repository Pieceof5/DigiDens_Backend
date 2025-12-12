package com.digidens.digidens_backend.mapper;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

import com.digidens.digidens_backend.dto.TaskCardTemplateDTO;
import com.digidens.digidens_backend.model.TaskCard;
import com.digidens.digidens_backend.model.TaskCardCell;
import com.digidens.digidens_backend.dto.ColumnDTO;
import com.digidens.digidens_backend.dto.RowDTO;

public class TaskCardMapper {

    public static TaskCardTemplateDTO toDTO(TaskCard card) {
        TaskCardTemplateDTO dto = new TaskCardTemplateDTO();
        dto.taskCardId = card.getId();
        dto.title = card.getName();

        // Columns → DTO
        dto.columns = card.getColumns().stream()
                .map(col -> {
                    ColumnDTO c = new ColumnDTO();
                    c.columnId = col.getId();
                    c.label = col.getName();
                    c.type = col.getType();
                    c.role = col.getRole();
                    c.options = col.getOptions() != null ? col.getOptions() : List.of();
                    return c;
                })
                .collect(Collectors.toList());

        // Rows → DTO
        dto.rows = card.getRows().stream()
                .map(row -> {
                    RowDTO r = new RowDTO();
                    r.rowId = row.getId();
                    // Solut: vain columnName → value
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
