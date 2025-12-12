package com.digidens.digidens_backend.service;

import org.springframework.stereotype.Service;

import com.digidens.digidens_backend.dto.StudentCourseProgressDTO;
import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.model.User;
import com.digidens.digidens_backend.repository.CourseInstanceRepository;
import com.digidens.digidens_backend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final UserRepository userRepository;
    private final CourseInstanceRepository courseInstanceRepository;

    public StudentService(UserRepository userRepository, CourseInstanceRepository courseInstanceRepository) {
        this.userRepository = userRepository;
        this.courseInstanceRepository = courseInstanceRepository;
    }

    /**
     * Hakee kaikki kurssit, joissa opiskelija on mukana, ja palauttaa DTO:n.
     * @param id opiskelijan ID
     * @return lista StudentCourseProgressDTO
     */
    public List<StudentCourseProgressDTO> getCoursesWithProgressByStudentId(Long id) {
        // Hae käyttäjä tietokannasta
        User student = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));

        // Varmista, että käyttäjä on opiskelija
        if (!"STUDENT".equals(student.getRole())) {
            throw new RuntimeException("User is not a student: " + id);
        }

        // Hae kaikki kurssitoteutukset, joissa opiskelija on mukana
        List<CourseInstance> instances = courseInstanceRepository.findAllByStudentsContaining(student);

        // Muunna DTO:iksi ja lisää mock-edistymistiedot
        return instances.stream()
                .map(ci -> {
                    int totalTasks = (int) (Math.random() * 10 + 5); // mock-tehtävien määrä
                    int completedTasks = (int) (Math.random() * (totalTasks + 1)); // mock-edistyminen

                    return new StudentCourseProgressDTO(
                            ci.getId(),
                            ci.getCourse() != null ? ci.getCourse().getName() : "(Ei kurssia)",
                            ci.getInstanceCode(),
                            ci.getStartDate(),
                            ci.getEndDate(),
                            completedTasks,
                            totalTasks
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * Hakee kaikki opiskelijat tietylle kurssitoteutukselle ja palauttaa DTO:n.
     * @param courseInstanceId kurssitoteutuksen ID
     * @return lista StudentCourseProgressDTO
     */
    public List<StudentCourseProgressDTO> getStudentsWithProgressByCourseInstanceId(Long courseInstanceId) {
        // Hae kurssitoteutus tietokannasta
        CourseInstance instance = courseInstanceRepository.findById(courseInstanceId)
                .orElseThrow(() -> new RuntimeException("Course instance not found: " + courseInstanceId));

        // Muunna kaikki opiskelijat DTO:iksi ja lisää mock-edistymistiedot
        return instance.getStudents().stream()
                .map(student -> {
                    int totalTasks = (int) (Math.random() * 10 + 5);
                    int completedTasks = (int) (Math.random() * (totalTasks + 1));

                    return new StudentCourseProgressDTO(
                            instance.getId(),
                            instance.getCourse() != null ? instance.getCourse().getName() : "(Ei kurssia)",
                            instance.getInstanceCode(),
                            instance.getStartDate(),
                            instance.getEndDate(),
                            completedTasks,
                            totalTasks
                    );
                })
                .collect(Collectors.toList());
    }
}