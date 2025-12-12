package com.digidens.digidens_backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.digidens.digidens_backend.model.User;

/**
 * DTO-luokka opettajan kurssitoteutuksille. Käytetään palauttamaan opettajan
 * tiedot ja hänen hallinnoimansa kurssit.
 */
public class TeacherCourseInstancesDTO {

	private Long teacherId; // Opettajan ID
	private String firstname; // Etunimi
	private String lastname; // Sukunimi
	private List<CourseInstanceDTO> courseInstances; // Lista kurssitoteutuksista

	// Getterit ja setterit
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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

	public List<CourseInstanceDTO> getCourseInstances() {
		return courseInstances;
	}

	public void setCourseInstances(List<CourseInstanceDTO> courseInstances) {
		this.courseInstances = courseInstances;
	}

	/**
	 * Sisäinen DTO kurssin toteutuksille. Sisältää kurssin perustiedot ja
	 * opiskelijat.
	 */
	public static class CourseInstanceDTO {
		private Long instanceId; // Kurssitoteutuksen ID
		private String instanceCode; // Toteutuksen koodi
		private String courseName; // Kurssin nimi
		private String courseCode; // Kurssin koodi
		private String gradeLevel; // Koulutusaste
		private List<StudentDTO> students; // Kurssille ilmoittautuneet opiskelijat
		private String startDate; // Aloituspäivämäärä merkkijonona
		private String endDate; // Lopetuspäivämäärä merkkijonona

		// Getterit ja setterit
		public Long getInstanceId() {
			return instanceId;
		}

		public void setInstanceId(Long instanceId) {
			this.instanceId = instanceId;
		}

		public String getInstanceCode() {
			return instanceCode;
		}

		public void setInstanceCode(String instanceCode) {
			this.instanceCode = instanceCode;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		public String getCourseCode() {
			return courseCode;
		}

		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}

		public String getGradeLevel() {
			return gradeLevel;
		}

		public void setGradeLevel(String gradeLevel) {
			this.gradeLevel = gradeLevel;
		}

		public List<StudentDTO> getStudents() {
			return students;
		}

		public void setStudents(List<StudentDTO> students) {
			this.students = students;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
	}

	/**
	 * DTO opiskelijoille, jotta salasana ja muut arkaluonteiset tiedot eivät mene
	 * frontendille.
	 */
	public static class StudentDTO {
		private Long id; // Opiskelijan ID
		private String firstname; // Etunimi
		private String lastname; // Sukunimi
		private String username; // Käyttäjätunnus
		private String studentNumber; // Opiskelijanumero
		private String gradeLevel; // Koulutusaste

		/**
		 * Rakentaja User-entityn pohjalta.
		 */
		public StudentDTO(User user) {
			this.id = user.getId();
			this.firstname = user.getFirstname();
			this.lastname = user.getLastname();
			this.username = user.getUsername();
			this.studentNumber = user.getStudentNumber();
			this.gradeLevel = user.getGradeLevel();
		}

		// Getterit
		public Long getId() {
			return id;
		}

		public String getFirstname() {
			return firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public String getUsername() {
			return username;
		}

		public String getStudentNumber() {
			return studentNumber;
		}

		public String getGradeLevel() {
			return gradeLevel;
		}
	}

	/**
	 * Helper-metodi, joka muuntaa CourseInstance-entityn CourseInstanceDTO:ksi.
	 */
	public static CourseInstanceDTO fromEntity(com.digidens.digidens_backend.model.CourseInstance ci) {
		CourseInstanceDTO dto = new CourseInstanceDTO();
		dto.setInstanceId(ci.getId());
		dto.setInstanceCode(ci.getInstanceCode());
		dto.setCourseName(ci.getCourse() != null ? ci.getCourse().getName() : "");
		dto.setCourseCode(ci.getCourse() != null ? ci.getCourse().getCourseCode() : "");
		dto.setGradeLevel(ci.getCourse() != null ? ci.getCourse().getGradeLevel() : "");
		dto.setStartDate(ci.getStartDate() != null ? ci.getStartDate().toString() : null);
		dto.setEndDate(ci.getEndDate() != null ? ci.getEndDate().toString() : null);
		dto.setStudents(
				ci.getStudents() != null ? ci.getStudents().stream().map(StudentDTO::new).collect(Collectors.toList())
						: List.of());
		return dto;
	}
}