package com.digidens.digidens_backend.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String courseCode; // Kurssin tunnus
	private String name; // Kurssin nimi
	private String gradeLevel; // Kurssin taso/luokka

	// Yhteen kurssiin voi kuulua monta toteutusta
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CourseInstance> courseInstances = new HashSet<>();

	public Course() {
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public Set<CourseInstance> getCourseInstances() {
		return courseInstances;
	}

	public void setCourseInstances(Set<CourseInstance> courseInstances) {
		this.courseInstances = courseInstances;
	}

	// Avustajametodi kurssitoteutuksen lisäämiseen kurssiiin
	public void addCourseInstance(CourseInstance instance) {
		instance.setCourse(this);
		this.courseInstances.add(instance);
	}
}
