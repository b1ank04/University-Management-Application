package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.service.GroupService;
import com.blank04.universitycms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/students")
public class StudentController {

    private static final String REDIRECT="redirect:/api/students";
    private static final String EXCEPTION="exception";

    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Student> students  = studentService.findAll();
        students.sort(Comparator.comparingLong(Student::getId));
        model.addAttribute("students", students);
        return "student/students-list";
    }

    @GetMapping("/signup")
    public String createStudentForm() {
        return "student/student-create";
    }

    @PostMapping("/create")
    public String createStudent(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                @RequestParam("group_id") Long groupId) {
        if (groupId == 0) {
            studentService.save(new Student(null, firstName, lastName));
            return REDIRECT;
        }
        Optional<Group> group = groupService.findById(groupId);
        if (group.isPresent()) {
            studentService.save(new Student(null, firstName, lastName, group.get()));
            return REDIRECT;
        } else {
            return EXCEPTION;
        }
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            model.addAttribute("first_name", student.get().getFirstName());
            model.addAttribute("last_name", student.get().getLastName());
            model.addAttribute("group_id", student.get().getGroup() != null ? student.get().getGroup().getId() : 0);
            return "student/student-update";
        } else {
            return EXCEPTION;
        }
    }

    @PostMapping("/update")
    public String updateStudent(@RequestParam("id") Long id, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                @RequestParam("group_id") Long groupId) {
        if (groupId == 0) {
            studentService.save(new Student(id, firstName, lastName, null));
            return REDIRECT;
        }
        Optional<Student> student = studentService.findById(id);
        Optional<Group> group = groupService.findById(groupId);
        if (group.isPresent() && student.isPresent()) {
            studentService.save(new Student(id, firstName, lastName, group.get()));
            return REDIRECT;
        } else {
            return EXCEPTION;
        }
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) throws SQLException {
        studentService.deleteById(id);
        return REDIRECT;
    }
}
