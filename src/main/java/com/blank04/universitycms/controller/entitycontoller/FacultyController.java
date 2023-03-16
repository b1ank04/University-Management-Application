package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("faculties", facultyService.findAll());
        return "faculties-list";
    }
}
