package com.digidens.digidens_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digidens.digidens_backend.dto.TaskCardTemplateDTO;
import com.digidens.digidens_backend.mapper.TaskCardTemplateMapper;
import com.digidens.digidens_backend.model.CourseInstance;
import com.digidens.digidens_backend.model.TaskCardTemplate;
import com.digidens.digidens_backend.repository.CourseInstanceRepository;
import com.digidens.digidens_backend.repository.TaskCardTemplateRepository;
import com.digidens.digidens_backend.service.TaskCardTemplateService;

/**
 * TaskCardTemplateController
 *
 * Tämä controller tarjoaa REST API -endpointit TaskCardTemplate-objekteille.
 * Endpointit mahdollistavat:
 *  - kaikkien suoritekorttipohjien haun
 *  - suoritekorttipohjien haun tietylle kurssitoteutukselle
 *  - uusien suoritekorttipohjien luomisen 
 */
@RestController
@RequestMapping("/api/taskCardTemplates")
public class TaskCardTemplateController {

    @Autowired
    private TaskCardTemplateRepository templateRepository; // Repositorio tietokantahakuja varten

    @Autowired
    private CourseInstanceRepository courseInstanceRepository; // Kurssitoteutusten hakuja varten

    @Autowired
    private TaskCardTemplateService templateService; // Liiketoimintalogiikka templatejen tallennukseen

    // --- Hae kaikki template:t (DTO-muodossa) ---
    @GetMapping
    public List<TaskCardTemplateDTO> getAllTemplates() {
        // Haetaan kaikki template:t tietokannasta ja muutetaan DTO-muotoon
        return templateRepository.findAll()
                .stream()
                .map(TaskCardTemplateMapper::toDTO)
                .toList();
    }

    // --- Hae template:t tietylle kurssitoteutukselle ---
    @GetMapping("/courseInstance/{courseInstanceId}")
    public List<TaskCardTemplateDTO> getTemplatesForCourseInstance(@PathVariable Long courseInstanceId) {
        // Haetaan kurssitoteutus annetulla ID:llä
        CourseInstance instance = courseInstanceRepository.findById(courseInstanceId)
                .orElseThrow(() -> new RuntimeException("CourseInstance not found"));

        // Haetaan kaikki template:t, jotka liittyvät kyseiseen kurssitoteutukseen ja muutetaan DTO-muotoon
        return templateRepository.findAllByCourseInstance(instance)
                .stream()
                .map(TaskCardTemplateMapper::toDTO)
                .toList();
    }

    // --- Luo uusi template tietylle kurssitoteutukselle DTO:n kautta ---
    @PostMapping("/createForCourseInstance/{courseInstanceId}")
    public TaskCardTemplate createTemplateForCourse(
            @PathVariable Long courseInstanceId,
            @RequestBody TaskCardTemplateDTO dto) {

        // Haetaan kurssitoteutus
        CourseInstance instance = courseInstanceRepository.findById(courseInstanceId)
                .orElseThrow(() -> new RuntimeException("CourseInstance not found"));

        // Tallennetaan uusi template service-luokan avulla
        return templateService.saveTemplate(dto, instance);
    }
}
