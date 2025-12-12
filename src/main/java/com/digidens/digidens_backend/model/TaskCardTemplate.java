package com.digidens.digidens_backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * TaskCardTemplate on suoritekortin mallipohja. Mallipohja sisältää: -
 * sarakkeet (columns) - rivit (rows) Tätä mallipohjaa voidaan käyttää
 * oppilaskohtaisten TaskCard-objektien luomiseen.
 */
@Entity
public class TaskCardTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Template ID

	private String name; // Template nimi, esim. "Suoritekortti 2025"

	/**
	 * Sarakkeet, jotka kuuluvat templateen. CascadeType.ALL: kaikki muutokset
	 * siirtyvät sarakkeisiin. orphanRemoval=true: sarakkeet poistetaan, jos ne
	 * eivät kuulu enää templateen.
	 */
	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<TaskCardColumn> columns = new ArrayList<>();

	/**
	 * Rivit, jotka kuuluvat templateen. Sama malli kuin sarakkeilla.
	 */
	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<TaskCardRow> rows = new ArrayList<>();

	/**
	 * Template voi kuulua tietylle kurssitoteutukselle.
	 */
	@ManyToOne
	private CourseInstance courseInstance;

	// --- Getterit ja Setterit ---
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskCardColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<TaskCardColumn> columns) {
		this.columns = columns;
		if (columns != null) {
			// Aseta jokaisen sarakkeen viittaus tähän templateen
			columns.forEach(c -> c.setTemplate(this));
		}
	}

	public List<TaskCardRow> getRows() {
		return rows;
	}

	public void setRows(List<TaskCardRow> rows) {
		this.rows = rows;
		if (rows != null) {
			// Aseta jokaisen rivin viittaus tähän templateen
			rows.forEach(r -> r.setTemplate(this));
		}
	}

	public CourseInstance getCourseInstance() {
		return courseInstance;
	}

	public void setCourseInstance(CourseInstance courseInstance) {
		this.courseInstance = courseInstance;
	}

	// --- Utility-metodit ---
	/**
	 * Lisää sarakkeen mallipohjaan. Asettaa viittauksen templateen automaattisesti.
	 */
	public void addColumn(TaskCardColumn column) {
		column.setTemplate(this);
		this.columns.add(column);
	}

	/**
	 * Lisää rivin mallipohjaan. Asettaa viittauksen templateen automaattisesti.
	 */
	public void addRow(TaskCardRow row) {
		row.setTemplate(this);
		this.rows.add(row);
	}
}
