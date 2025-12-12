package com.digidens.digidens_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.digidens.digidens_backend.model.TaskCard;
import com.digidens.digidens_backend.model.TaskCardCell;
import com.digidens.digidens_backend.model.TaskCardTemplate;

import jakarta.persistence.*;

@Entity
public class TaskCardRow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "template_id")
	@JsonBackReference("rowsReference")
	private TaskCardTemplate template;

	@ManyToOne
	@JoinColumn(name = "task_card_id")
	@JsonBackReference
	private TaskCard taskCard;

	@OneToMany(mappedBy = "row", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("cellsReference")
	private List<TaskCardCell> cells = new ArrayList<>();

	// Getters & Setters
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

	public TaskCardTemplate getTemplate() {
		return template;
	}

	public void setTemplate(TaskCardTemplate template) {
		this.template = template;
	}

	public TaskCard getTaskCard() {
		return taskCard;
	}

	public void setTaskCard(TaskCard taskCard) {
		this.taskCard = taskCard;
	}

	public List<TaskCardCell> getCells() {
		return cells;
	}

	public void setCells(List<TaskCardCell> cells) {
		this.cells = cells;
	}
}
