package com.digidens.digidens_backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class TaskCardColumn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String role; // STUDENT tai TEACHER
	private String type; // TEXT, MULTIPLE_CHOICE, CHECKBOX

	@ElementCollection
	private List<String> options; // vain MULTIPLE_CHOICE

	@ManyToOne
	@JoinColumn(name = "template_id")

	@JsonIgnore
	private TaskCardTemplate template;

	@ManyToOne
	@JoinColumn(name = "task_card_id")

	@JsonIgnore
	private TaskCard taskCard;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
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
}