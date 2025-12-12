package com.digidens.digidens_backend.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TaskCard on oppilaskohtainen suoritekortti. Mallipohja (TaskCardTemplate)
 * luodaan tästä kopiona. Sisältää sarakkeet ja rivit opiskelijan näkymälle.
 */
@Entity
public class TaskCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // TaskCardin ID

	private String name; // TaskCardin nimi, esim. "Suoritekortti - Matti Meikäläinen"

	private Long studentId; // Opiskelijan ID, jolle tämä kortti kuuluu

	/**
	 * Kurssitoteutus, johon TaskCard liittyy
	 */
	@ManyToOne
	private CourseInstance courseInstance;

	/**
	 * TaskCardin sarakkeet CascadeType.ALL: muutokset siirtyvät sarakkeisiin
	 * 
	 * @JsonIgnore estää backreference-syklit JSON-serialisoinnissa
	 */
	@OneToMany(mappedBy = "taskCard", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TaskCardColumn> columns;

	/**
	 * TaskCardin rivit Sama malli kuin sarakkeilla
	 */
	@OneToMany(mappedBy = "taskCard", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TaskCardRow> rows;

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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public CourseInstance getCourseInstance() {
		return courseInstance;
	}

	public void setCourseInstance(CourseInstance courseInstance) {
		this.courseInstance = courseInstance;
	}

	public List<TaskCardColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<TaskCardColumn> columns) {
		this.columns = columns;
	}

	public List<TaskCardRow> getRows() {
		return rows;
	}

	public void setRows(List<TaskCardRow> rows) {
		this.rows = rows;
	}
}
