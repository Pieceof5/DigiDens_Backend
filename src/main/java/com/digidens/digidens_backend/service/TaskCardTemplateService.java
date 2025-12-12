package com.digidens.digidens_backend.service;

import com.digidens.digidens_backend.dto.ColumnDTO;
import com.digidens.digidens_backend.dto.RowDTO;
import com.digidens.digidens_backend.dto.TaskCardTemplateDTO;
import com.digidens.digidens_backend.model.*;
import com.digidens.digidens_backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Palveluluokka, joka huolehtii suoritekorttien mallipohjien (TaskCardTemplate)
 * luomisesta ja tallentamisesta.
 */
@Service
public class TaskCardTemplateService {

	private final TaskCardTemplateRepository templateRepository;
	private final TaskCardColumnRepository columnRepository;
	private final TaskCardRowRepository rowRepository;
	private final TaskCardCellRepository cellRepository;

	public TaskCardTemplateService(TaskCardTemplateRepository templateRepository,
			TaskCardColumnRepository columnRepository, TaskCardRowRepository rowRepository,
			TaskCardCellRepository cellRepository) {
		this.templateRepository = templateRepository;
		this.columnRepository = columnRepository;
		this.rowRepository = rowRepository;
		this.cellRepository = cellRepository;
	}

	/**
	 * Luo ja tallentaa uuden suoritekortin mallipohjan kurssitoteutukselle. Kloonaa
	 * sarakkeet, rivit ja solut DTO:n perusteella.
	 *
	 * @param dto            sisältää template-tiedot (sarakkeet, rivit, solut)
	 * @param courseInstance kurssitoteutus, johon template liitetään
	 * @return tallennettu TaskCardTemplate
	 */
	@Transactional
	public TaskCardTemplate saveTemplate(TaskCardTemplateDTO dto, CourseInstance courseInstance) {
		TaskCardTemplate template = new TaskCardTemplate();
		template.setName(dto.title);
		template.setCourseInstance(courseInstance);

		// Lisää sarakkeet
		if (dto.columns != null) {
			for (ColumnDTO colDto : dto.columns) {
				TaskCardColumn col = new TaskCardColumn();
				col.setName(colDto.label);
				col.setType(colDto.type);
				col.setRole(colDto.role);
				col.setOptions(colDto.options);
				template.addColumn(col);
			}
		}

		// Lisää rivit ja solut
		if (dto.rows != null) {
			for (RowDTO rowDto : dto.rows) {
				TaskCardRow row = new TaskCardRow();
				row.setName("Row " + (rowDto.rowId != null ? rowDto.rowId : ""));
				template.addRow(row);

				if (rowDto.cells != null) {
					rowDto.cells.forEach((colName, value) -> {
						TaskCardCell cell = new TaskCardCell();
						cell.setColumnName(colName);
						cell.setCellValue(value);
						cell.setRow(row);
						row.getCells().add(cell);
					});
				}
			}
		}

		return templateRepository.save(template);
	}
}
