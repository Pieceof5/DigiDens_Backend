package com.digidens.digidens_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CourseInstance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String instanceCode; // Kurssitoteutuksen koodi: kurssin courseCode + sarjanumero

	// Moni toteutus kuuluu yhteen kurssiin
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	@JsonIgnoreProperties({ "courseInstances" })
	@JsonIgnore
	private Course course;

	// Moni toteutus yhdelle opettajalle (voi olla null)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	@JsonIgnoreProperties({ "taughtCourseInstances" })
	@JsonIgnore
	private User teacher;

	// Monet opiskelijat monessa toteutuksessa
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "course_instance_students", joinColumns = @JoinColumn(name = "instance_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private Set<User> students = new HashSet<>();

	private LocalDate startDate; // Toteutuksen aloituspäivä
	private LocalDate endDate; // Toteutuksen loppupäivä

	public CourseInstance() {
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstanceCode() {
		return instanceCode;
	}

	public void setInstanceCode(String instanceCode) {
		this.instanceCode = instanceCode;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Set<User> getStudents() {
		return students;
	}

	public void setStudents(Set<User> students) {
		this.students = students;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	//  lisää opiskelija toteutukseen
	public void addStudent(User student) {
		this.students.add(student);
	}

	// poista opiskelija toteutuksesta
	public void removeStudent(User student) {
		this.students.remove(student);
	}
}
