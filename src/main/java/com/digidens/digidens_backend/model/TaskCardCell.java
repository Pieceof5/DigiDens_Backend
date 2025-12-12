package com.digidens.digidens_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * TaskCardCell edustaa yhtä solua suoritekortin rivillä. Solulla on nimi
 * (columnName) ja arvo (cellValue), ja se kuuluu tiettyyn TaskCardRow:iin.
 */
@Entity
public class TaskCardCell {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Solun ID

	private String columnName; // Solun sarakkeen nimi
	private String cellValue; // Solun tallennettu arvo

	/**
	 * Solu kuuluu tietylle riville. JsonIgnore estää syklisen viittauksen
	 * JSON-serialisoinnissa.
	 */
	@ManyToOne
	@JoinColumn(name = "row_id")
	@JsonIgnore
	private TaskCardRow row;

	// --- Getterit ja Setterit ---
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCellValue() {
		return cellValue;
	}

	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	public TaskCardRow getRow() {
		return row;
	}

	public void setRow(TaskCardRow row) {
		this.row = row;
	}
}
