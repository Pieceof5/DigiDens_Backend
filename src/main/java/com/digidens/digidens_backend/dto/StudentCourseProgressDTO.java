package com.digidens.digidens_backend.dto;

import java.time.LocalDate;

/**
 * DTO opiskelijan edistymisen näyttämiseen kurssilla. Sisältää kurssin tiedot
 * ja suoritettujen tehtävien määrän.
 */
public class StudentCourseProgressDTO {

	private Long courseInstanceId; // Kurssin toteutuksen ID
	private String courseName; // Kurssin nimi
	private String instanceCode; // Kurssin toteutuksen koodi
	private LocalDate startDate; // Toteutuksen aloituspäivä
	private LocalDate endDate; // Toteutuksen loppupäivä
	private int completedTasks; // Suoritettujen tehtävien määrä
	private int totalTasks; // Tehtävien kokonaismäärä
	private int progressPercentage; // Edistyminen prosentteina

	/**
	 * Konstruktorissa asetetaan kurssin tiedot ja lasketaan edistymisprosentti.
	 */
	public StudentCourseProgressDTO(Long courseInstanceId, String courseName, String instanceCode, LocalDate startDate,
			LocalDate endDate, int completedTasks, int totalTasks) {
		this.courseInstanceId = courseInstanceId;
		this.courseName = courseName;
		this.instanceCode = instanceCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.completedTasks = completedTasks;
		this.totalTasks = totalTasks;
		this.progressPercentage = totalTasks > 0 ? (completedTasks * 100) / totalTasks : 0;
	}

	// Getterit ja setterit
	public Long getCourseInstanceId() {
		return courseInstanceId;
	}

	public void setCourseInstanceId(Long courseInstanceId) {
		this.courseInstanceId = courseInstanceId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstanceCode() {
		return instanceCode;
	}

	public void setInstanceCode(String instanceCode) {
		this.instanceCode = instanceCode;
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

	public int getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}

	public int getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public int getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(int progressPercentage) {
		this.progressPercentage = progressPercentage;
	}
}