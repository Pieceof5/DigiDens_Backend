package com.digidens.digidens_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.digidens.digidens_backend.model.TaskCard;
import com.digidens.digidens_backend.service.TaskCardService;

@RestController
@RequestMapping("/api/taskCards")
public class TaskCardController {

    @Autowired
    private TaskCardService taskCardService;

    /**
     * Luo opiskelijalle suoritekortin template:n perusteella.
     * Template on jo liitetty kurssitoteutukselle.
     * -> Siksi courseInstanceId EI TARVITA tässä.
     */
    @PostMapping("/fromTemplate/{templateId}/student/{studentId}")
    public TaskCard createTaskCardFromTemplate(
            @PathVariable Long templateId,
            @PathVariable Long studentId) {

        return taskCardService.createTaskCardForStudent(templateId, studentId);
    }
}
