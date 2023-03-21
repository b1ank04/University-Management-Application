package com.blank04.universitycms.controller;

import com.blank04.universitycms.controller.entitycontoller.AudienceController;
import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.service.AudienceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    void shouldCreateAudience() throws Exception {
        int floor = 1;
        mvc.perform(post("/api/audiences/create")
                        .param("floor", String.valueOf(floor)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/audiences"));

        verify(audienceService, times(1)).save(new Audience(null, floor));
    }

    @Test
    void shouldLoadUpdateAudienceForm() throws Exception {
        when(audienceService.findById(1L)).thenReturn(Optional.of(new Audience(1L, 1)));
        mvc.perform(MockMvcRequestBuilders.get("/api/audiences/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("audience/audience-update"))
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attribute("id", equalTo(1L)));
    }

    @Test
    void shouldNotLoadUpdateAudienceForm() throws Exception {
        when(audienceService.findById(1L)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/audiences/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("exception"));
    }

    @Test
    void shouldUpdateAudience() throws Exception {
        mvc.perform(post("/api/audiences/update")
                        .param("id", "1")
                        .param("floor", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/audiences"));
    }

    @Test
    void shouldDeleteAudience() throws Exception {
        Long id = 1L;
        when(audienceService.findById(id)).thenReturn(Optional.of(new Audience(1L, 1)));
        mvc.perform(MockMvcRequestBuilders.get("/api/audiences/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/api/audiences"));
        Mockito.verify(audienceService).deleteById(id);
    }
}