package com.blank04.universitycms.controller;

import com.blank04.universitycms.controller.entitycontoller.TeacherController;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.model.user.impl.Teacher;
import com.blank04.universitycms.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @MockBean
    TeacherService teacherService;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnListOfTeachersFromModelAttribute() throws Exception {
        List<Teacher> expectResult = List.of(new Teacher(1000L, "John", "Doe", new Subject(1000L, "test1")),
                new Teacher(1001L, "Jane", "Doe", new Subject(1001L, "test2")));
        when(teacherService.findAll())
                .thenReturn(expectResult);
        mvc.perform(MockMvcRequestBuilders.get("/api/teachers"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("teachers"))
                .andExpect(model().attribute("teachers", expectResult));
    }

    @Test
    void shouldReturnEmptyListFromModelAttribute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/teachers"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("teachers"))
                .andExpect(model().attribute("teachers", List.of()));
    }
}