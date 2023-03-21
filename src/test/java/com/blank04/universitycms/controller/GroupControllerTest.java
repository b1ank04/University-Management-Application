package com.blank04.universitycms.controller;

import com.blank04.universitycms.controller.entitycontoller.GroupController;
import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(GroupController.class)
class GroupControllerTest {

    @MockBean
    GroupService groupService;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnListOfGroupsFromModelAttribute() throws Exception {
        List<Group> expectResult = List.of(new Group(1000L, "Test1", new Faculty(1000L, "test1")),
                new Group(1001L, "Test2", new Faculty(1001L, "test2")));
        when(groupService.findAll())
                .thenReturn(expectResult);
        mvc.perform(MockMvcRequestBuilders.get("/api/groups"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attribute("groups", expectResult));
    }

    @Test
    void shouldReturnEmptyListFromModelAttribute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/groups"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attribute("groups", List.of()));
    }
}