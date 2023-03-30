package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/faculties")
public class FacultyController {

    private static final String REDIRECT="redirect:/api/faculties";
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Faculty> faculties = facultyService.findAll();
        faculties.sort(Comparator.comparingLong(Faculty::getId));
        model.addAttribute("faculties", faculties);
        return "faculty/faculties-list";
    }
    @GetMapping("/signup")
    public String createFacultyForm() {
        return "faculty/faculty-create";
    }

    @PostMapping("/create")
    public String createFaculty(@RequestParam("name") String name) {
        facultyService.save(new Faculty(null, name));
        return REDIRECT;
    }

    @GetMapping("/update/{id}")
    public String updateFacultyForm(@PathVariable("id") Long id, Model model) {
        Optional<Faculty> faculty = facultyService.findById(id);
        if (faculty.isPresent()) {
            model.addAttribute("name", faculty.get().getName());
            return "faculty/faculty-update";
        } else {
            return "exception";
        }
    }

    @PostMapping("/update")
    public String updateFaculty(@RequestParam("id") Long id, @RequestParam("name") String name) {
        facultyService.save(new Faculty(id, name));
        return REDIRECT;
    }

    @GetMapping("delete/{id}")
    public String deleteFaculty(@PathVariable("id") Long id) throws SQLException {
        facultyService.deleteById(id);
        return REDIRECT;
    }
}
