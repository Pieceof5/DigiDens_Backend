package com.digidens.digidens_backend.service;

import com.digidens.digidens_backend.model.*;
import com.digidens.digidens_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Palveluluokka, joka huolehtii opiskelijakohtaisten TaskCard-objektien
 * luomisesta mallipohjien (TaskCardTemplate) perusteella.
 */
@Service
public class TaskCardService {

	@Autowired
	private TaskCardRepository taskCardRepository;

	@Autowired
	private TaskCardTemplateRepository templateRepository;

	/**
	 * Luo uuden suoritekortin opiskelijalle annetun mallipohjan perusteella.
	 * Kloonaa sarakkeet, rivit ja solut.
	 *
	 * @param templateId käytettävän mallipohjan ID
	 * @param studentId  opiskelijan ID
	 * @return tallennettu TaskCard-objekti
	 */
	public TaskCard createTaskCardForStudent(Long templateId, Long studentId) {

		// Hae template tietokannasta
		TaskCardTemplate template = templateRepository.findById(templateId)
				.orElseThrow(() -> new RuntimeException("Template not found"));

		CourseInstance instance = template.getCourseInstance();
		if (instance == null) {
			throw new RuntimeException("Template is not linked to a course instance");
		}

		// Luo uusi TaskCard opiskelijalle
		TaskCard card = new TaskCard();
		card.setName(template.getName());
		card.setStudentId(studentId);
		card.setCourseInstance(instance);

		// --- kloonaa sarakkeet ---
		List<TaskCardColumn> newColumns = new ArrayList<>();
		for (TaskCardColumn templateCol : template.getColumns()) {
			TaskCardColumn newCol = new TaskCardColumn();
			newCol.setName(templateCol.getName());
			newCol.setType(templateCol.getType());
			newCol.setRole(templateCol.getRole());
			newCol.setOptions(templateCol.getOptions());
			newCol.setTaskCard(card); // linkki uuteen TaskCardiin
			newColumns.add(newCol);
		}
		card.setColumns(newColumns);

		// --- kloonaa rivit ja solut ---
		List<TaskCardRow> newRows = new ArrayList<>();
		for (TaskCardRow templateRow : template.getRows()) {
			TaskCardRow newRow = new TaskCardRow();
			newRow.setName(templateRow.getName());
			newRow.setTaskCard(card);

			List<TaskCardCell> newCells = new ArrayList<>();
			for (TaskCardCell templateCell : templateRow.getCells()) {
				TaskCardCell newCell = new TaskCardCell();
				newCell.setColumnName(templateCell.getColumnName());
				newCell.setRow(newRow); // linkki uuteen riviin
				newCells.add(newCell);
			}

			newRow.setCells(newCells);
			newRows.add(newRow);
		}
		card.setRows(newRows);

		// Tallenna ja palauta
		return taskCardRepository.save(card);
	}

	/**
	 * Hakee kaikki suoritekortit tietylle opiskelijalle.
	 *
	 * @param studentId opiskelijan ID
	 * @return lista TaskCard-objekteista
	 */
	public List<TaskCard> getTaskCardsByStudentId(Long studentId) {
		return taskCardRepository.findByStudentId(studentId);
	}
}
