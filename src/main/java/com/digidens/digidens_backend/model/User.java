package com.digidens.digidens_backend.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
/**
 * Käyttäjäjärjestelmän perusluokka. Sisältää tiedot käyttäjästä (opettaja tai
 * oppilas), kuten käyttäjätunnuksen, salasanan, roolin, etu- ja sukunimen,
 * opiskelijanumeron sekä luokkatasotiedon.
 */
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Yksilöllinen tunniste

	@Column(nullable = false, unique = true)
	private String username; // Käyttäjätunnus

	@Column(nullable = false)
	private String password; // Salasana

	@Column(nullable = false)
	private String role; // Käyttäjän rooli (opettaja/oppilas)

	@Column(nullable = false)
	private String firstname; // Etunimi

	@Column(nullable = false)
	private String lastname; // Sukunimi

	@Column(nullable = true, unique = true)
	private String studentNumber; // Opiskelijanumero (vain oppilailla)

	@Column(nullable = false)
	private String gradeLevel; // Luokkataso tai kurssitaso

	/**
	 * Kurssit, joita käyttäjä opettaa (opettaja). One-to-many -suhde
	 * CourseInstance-entityyn.
	 */
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<CourseInstance> taughtCourseInstances = new HashSet<>();

	/**
	 * Kurssit, joihin käyttäjä osallistuu (oppilas). Many-to-many -suhde
	 * CourseInstance-entityyn.
	 */
	@ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<CourseInstance> enrolledCourseInstances = new HashSet<>();

	// Parametriton konstruktori JPA:lle
	public User() {
	}

	// Konstruktori perusominaisuuksille
	public User(String username, String password, String role, String firstname, String lastname, String gradeLevel) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gradeLevel = gradeLevel;
	}

	// Getterit ja setterit
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public Set<CourseInstance> getTaughtCourseInstances() {
		return taughtCourseInstances;
	}

	public void setTaughtCourseInstances(Set<CourseInstance> taughtCourseInstances) {
		this.taughtCourseInstances = taughtCourseInstances;
	}

	public Set<CourseInstance> getEnrolledCourseInstances() {
		return enrolledCourseInstances;
	}

	public void setEnrolledCourseInstances(Set<CourseInstance> enrolledCourseInstances) {
		this.enrolledCourseInstances = enrolledCourseInstances;
	}
}
