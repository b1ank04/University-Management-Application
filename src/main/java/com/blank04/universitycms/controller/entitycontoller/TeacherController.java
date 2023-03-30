package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.model.user.impl.Teacher;
import com.blank04.universitycms.service.SubjectService;
import com.blank04.universitycms.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/teachers")
public class TeacherController {

    private static final String REDIRECT="redirect:/api/teachers";
    private static final String EXCEPTION="exception";

    private final TeacherService teacherService;
    private final SubjectService subjectService;

    public TeacherController(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        teachers.sort(Comparator.comparingLong(Teacher::getId));
        model.addAttribute("teachers", teachers);
        return "teacher/teachers-list";
    }

    @GetMapping("/signup")
    public String createTeacherForm() {
        return "teacher/teacher-create";
    }

    @PostMapping("/create")
    public String createStudent(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                @RequestParam("subject_id") Long subjectId) {
        if (subjectId == 0) {
            teacherService.save(new Teacher(null, firstName, lastName, null));
            return REDIRECT;
        }

        Optional<Subject> subject = subjectService.findById(subjectId);
        if (subject.isPresent()) {
            teacherService.save(new Teacher(null, firstName, lastName, subject.get()));
            return REDIRECT;
        } else {
            return EXCEPTION;
        }
    }

    @GetMapping("/update/{id}")
    public String updateTeacherForm(@PathVariable("id") Long id, Model model) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isPresent()) {
            model.addAttribute("first_name", teacher.get().getFirstName());
            model.addAttribute("last_name", teacher.get().getLastName());
            model.addAttribute("subject_id", teacher.get().getSubject() != null ? teacher.get().getSubject().getId() : 0);
            return "teacher/teacher-update";
        } else {
            return EXCEPTION;
        }
    }

    @PostMapping("/update")
    public String updateTeacher(@RequestParam("id") Long id, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                @RequestParam("subject_id") Long subjectId) {
        if (subjectId == 0) {
            teacherService.save(new Teacher(id, firstName, lastName, null));
            return REDIRECT;
        }

        Optional<Teacher> teacher = teacherService.findById(id);
        Optional<Subject> subject = subjectService.findById(subjectId);
        if (teacher.isPresent() && subject.isPresent()) {
            teacherService.save(new Teacher(id, firstName, lastName, subject.get()));
            return REDIRECT;
        } else {
            return EXCEPTION;
        }
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) throws SQLException {
        teacherService.deleteById(id);
        return REDIRECT;
    }
}
