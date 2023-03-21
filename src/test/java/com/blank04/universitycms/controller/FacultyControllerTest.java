package com.blank04.universitycms.controller;

import com.blank04.universitycms.controller.entitycontoller.FacultyController;
import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {
    @MockBean
    FacultyService facultyService;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnListOfFacultiesFromModelAttribute() throws Exception {
        List<Faculty> expectResult = List.of(new Faculty(1000L, "Test1"), new Faculty(1001L, "Test2"));
        when(facultyService.findAll())
                .thenReturn(expectResult);
        mvc.perform(MockMvcRequestBuilders.get("/api/faculties"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("faculties"))
                .andExpect(model().attribute("faculties", expectResult));
    }

    @Test
    void shouldReturnEmptyListFromModelAttribute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/faculties"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("faculties"))
                .andExpect(model().attribute("faculties", List.of()));
    }

}