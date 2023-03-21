package com.blank04.universitycms.controller;

import com.blank04.universitycms.controller.entitycontoller.SubjectController;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@WebMvcTest(SubjectController.class)
class SubjectControllerTest {

    @MockBean
    SubjectService subjectService;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnListOfSubjectsFromModelAttribute() throws Exception {
        List<Subject> expectResult = List.of(new Subject(1000L, "test1"), new Subject(1001L, "test2"));
        when(subjectService.findAll())
                .thenReturn(expectResult);
        mvc.perform(MockMvcRequestBuilders.get("/api/subjects"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("subjects"))
                .andExpect(model().attribute("subjects", expectResult));
    }

    @Test
    void shouldReturnEmptyListFromModelAttribute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/subjects"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("subjects"))
                .andExpect(model().attribute("subjects", List.of()));
    }
}