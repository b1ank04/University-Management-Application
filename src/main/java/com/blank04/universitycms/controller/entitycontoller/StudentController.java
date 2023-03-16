package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Student> students  = studentService.findAll();
        model.addAttribute("students", students);
        return "students-list";
    }
}
