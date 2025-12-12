package com.digidens.digidens_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digidens.digidens_backend.dto.CourseInstanceDTO;
import com.digidens.digidens_backend.dto.StudentCourseProgressDTO;
import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.repository.CourseInstanceRepository;
import com.digidens.digidens_backend.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course-instances")
public class CourseInstanceController {

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private StudentService studentService;

    // --- Hae kaikki kurssitoteutukset DTO:ina ---
    @GetMapping
    public List<CourseInstanceDTO> getAllCourseInstances() {
        return courseInstanceRepository.findAll().stream()
                .map(CourseInstanceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // --- Hae yksittäinen kurssitoteutus DTO:na ---
    @GetMapping("/{id}")
    public ResponseEntity<CourseInstanceDTO> getCourseInstanceById(@PathVariable Long id) {
        return courseInstanceRepository.findById(id)
                .map(CourseInstanceDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- Hae kaikki kurssitoteutukset tietylle kurssille ---
    @GetMapping("/course/{courseId}")
    public List<CourseInstanceDTO> getCourseInstancesByCourse(@PathVariable Long courseId) {
        return courseInstanceRepository.findAllByCourseId(courseId).stream()
                .map(CourseInstanceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // --- Hae kaikki kurssitoteutukset tietylle opettajalle ---
    @GetMapping("/teacher/{teacherId}")
    public List<CourseInstanceDTO> getCourseInstancesByTeacher(@PathVariable Long teacherId) {
        return courseInstanceRepository.findAllByTeacherId(teacherId).stream()
                .map(CourseInstanceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // --- Luo uusi kurssitoteutus ---
    @PostMapping
    public CourseInstance createCourseInstance(@RequestBody CourseInstance courseInstance) {
        return courseInstanceRepository.save(courseInstance);
    }

    // --- Päivitä kurssitoteutus ---
    @PutMapping("/{id}")
    public CourseInstance updateCourseInstance(@PathVariable Long id, @RequestBody CourseInstance updatedInstance) {
        return courseInstanceRepository.findById(id)
                .map(instance -> {
                    instance.setCourse(updatedInstance.getCourse());
                    instance.setTeacher(updatedInstance.getTeacher());
                    instance.setStudents(updatedInstance.getStudents());
                    instance.setStartDate(updatedInstance.getStartDate());
                    instance.setEndDate(updatedInstance.getEndDate());
                    instance.setInstanceCode(updatedInstance.getInstanceCode());
                    return courseInstanceRepository.save(instance);
                })
                .orElseGet(() -> {
                    updatedInstance.setId(id);
                    return courseInstanceRepository.save(updatedInstance);
                });
    }

    // --- Poista kurssitoteutus ---
    @DeleteMapping("/{id}")
    public void deleteCourseInstance(@PathVariable Long id) {
        courseInstanceRepository.deleteById(id);
    }

    // --- Hae opiskelijat ja edistyminen yhdelle kurssitoteutukselle ---
    @GetMapping("/{id}/students")
    public List<StudentCourseProgressDTO> getStudentsWithProgress(@PathVariable Long id) {
        return studentService.getStudentsWithProgressByCourseInstanceId(id);
    }
}