package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.repository.AudienceRepository;
import com.blank04.universitycms.service.impl.AudienceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AudienceServiceImpl.class})
class AudienceServiceTest {

    @MockBean
    private AudienceRepository mockedRepository;

    @Autowired
    private AudienceService audienceService;

    @Test
    void shouldDeleteById() throws SQLException {
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(Mockito.mock(Audience.class)));
        when(mockedRepository.findById(1001L)).thenReturn(Optional.of(Mockito.mock(Audience.class)));
        audienceService.deleteById(1000L);
        audienceService.deleteById(1001L);
        assertEquals(new ArrayList<>(), audienceService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        when(mockedRepository.findById(1L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(SQLException.class, () -> audienceService.deleteById(1L));
        assertEquals("Audience with id=1 doesn't exist", thrown.getMessage());
    }
}
