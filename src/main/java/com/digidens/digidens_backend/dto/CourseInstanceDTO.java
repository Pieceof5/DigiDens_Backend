package com.digidens.digidens_backend.dto;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.model.User;

/**
 * DTO kurssitoteutukselle (CourseInstance)
 * Sisältää kurssin tiedot, opettajan ja opiskelijat.
 */
public class CourseInstanceDTO {

    private Long id;
    private String instanceCode;
    private String courseName;
    private String courseCode;
    private String gradeLevel;
    private Long teacherId;
    private String teacherFirstname;
    private String teacherLastname;
    private List<StudentDTO> students;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getterit ja setterit
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInstanceCode() { return instanceCode; }
    public void setInstanceCode(String instanceCode) { this.instanceCode = instanceCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(String gradeLevel) { this.gradeLevel = gradeLevel; }

    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    public String getTeacherFirstname() { return teacherFirstname; }
    public void setTeacherFirstname(String teacherFirstname) { this.teacherFirstname = teacherFirstname; }

    public String getTeacherLastname() { return teacherLastname; }
    public void setTeacherLastname(String teacherLastname) { this.teacherLastname = teacherLastname; }

    public List<StudentDTO> getStudents() { return students; }
    public void setStudents(List<StudentDTO> students) { this.students = students; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    // DTO opiskelijoille, jotta salasana tms. ei mene frontendille
    public static class StudentDTO {
        private Long id;
        private String firstname;
        private String lastname;
        private String username;
        private String studentNumber;
        private String gradeLevel;

        public StudentDTO(User user) {
            this.id = user.getId();
            this.firstname = user.getFirstname();
            this.lastname = user.getLastname();
            this.username = user.getUsername();
            this.studentNumber = user.getStudentNumber();
            this.gradeLevel = user.getGradeLevel();
        }

        // Getterit
        public Long getId() { return id; }
        public String getFirstname() { return firstname; }
        public String getLastname() { return lastname; }
        public String getUsername() { return username; }
        public String getStudentNumber() { return studentNumber; }
        public String getGradeLevel() { return gradeLevel; }
    }

    /**
     * Helper-metodi entity -> DTO
     */
    public static CourseInstanceDTO fromEntity(CourseInstance ci) {
        CourseInstanceDTO dto = new CourseInstanceDTO();
        dto.setId(ci.getId());
        dto.setInstanceCode(ci.getInstanceCode());
        dto.setStartDate(ci.getStartDate());
        dto.setEndDate(ci.getEndDate());

        if (ci.getCourse() != null) {
            dto.setCourseName(ci.getCourse().getName());
            dto.setCourseCode(ci.getCourse().getCourseCode());
            dto.setGradeLevel(ci.getCourse().getGradeLevel());
        }

        if (ci.getTeacher() != null) {
            dto.setTeacherId(ci.getTeacher().getId());
            dto.setTeacherFirstname(ci.getTeacher().getFirstname());
            dto.setTeacherLastname(ci.getTeacher().getLastname());
        }

        if (ci.getStudents() != null) {
            dto.setStudents(ci.getStudents().stream()
                    .map(StudentDTO::new)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
