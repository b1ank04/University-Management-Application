package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/subjects")
public class SubjectController {

    private static final String REDIRECT="redirect:/api/subjects";
    private static final String EXCEPTION="exception";
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Subject> subjects = subjectService.findAll();
        subjects.sort(Comparator.comparingLong(Subject::getId));
        model.addAttribute("subjects", subjects);
        return "subject/subjects-list";
    }

    @GetMapping("/signup")
    public String createSubjectForm() {
        return "subject/subject-create";
    }

    @PostMapping("/create")
    public String createSubject(@RequestParam("name") String name) {
        subjectService.save(new Subject(null, name));
        return REDIRECT;
    }

    @GetMapping("/update/{id}")
    public String updateSubjectForm(@PathVariable("id") Long id, Model model) {
        Optional<Subject> subject = subjectService.findById(id);
        if (subject.isPresent()) {
            model.addAttribute("id", subject.get().getId());
            model.addAttribute("name", subject.get().getName());
            return "subject/subject-update";
        } else {
            return EXCEPTION;
        }
    }

    @PostMapping("/update")
    public String updateAudience(@RequestParam("id") Long id, @RequestParam("name") String name) {
        subjectService.save(new Subject(id, name));
        return REDIRECT;
    }

    @GetMapping("delete/{id}")
    public String deleteAudience(@PathVariable("id") Long id) throws SQLException {
        subjectService.deleteById(id);
        return REDIRECT;
    }
}
