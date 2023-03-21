package com.blank04.universitycms.controller;

import com.blank04.universitycms.controller.entitycontoller.StudentController;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    StudentService studentService;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnListOfStudentsFromModelAttribute() throws Exception {
        List<Student> expectResult = List.of(new Student(1000L, "John", "Doe"),
                new Student(1001L, "Jane", "Doe"));
        when(studentService.findAll())
                .thenReturn(expectResult);
        mvc.perform(MockMvcRequestBuilders.get("/api/students"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().attribute("students", expectResult));
    }

    @Test
    void shouldReturnEmptyListFromModelAttribute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/students"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("students"))
                .andExpect(model().attribute("students", List.of()));
    }
}