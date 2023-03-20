package com.blank04.universitycms.controller;

import com.blank04.universitycms.controller.entitycontoller.AudienceController;
import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.service.AudienceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(AudienceController.class)
class AudienceControllerTest {

    @MockBean
    AudienceService audienceService;

    @Autowired
    MockMvc mvc;


    @Test
    void shouldReturnListOfAudiencesFromModelAttribute() throws Exception {
        List<Audience> expectResult = List.of(new Audience(1000L, 1000),
                new Audience(1001L, 1001));
        when(audienceService.findAll())
                .thenReturn(expectResult);
        mvc.perform(MockMvcRequestBuilders.get("/api/audiences"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("audiences"))
                .andExpect(model().attribute("audiences", expectResult));
    }

    @Test
    void shouldReturnEmptyListFromModelAttribute() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/api/audiences"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("audiences"))
                .andExpect(model().attribute("audiences", List.of()));
    }
}