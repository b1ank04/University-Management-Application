package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.repository.SubjectRepository;
import com.blank04.universitycms.service.impl.SubjectServiceImpl;
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

@SpringBootTest(classes = SubjectServiceImpl.class)
class SubjectServiceTest {

    @MockBean
    SubjectRepository mockedRepository;

    @Autowired
    SubjectService subjectService;

    @Test
    void shouldDeleteById() throws SQLException {
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(Mockito.mock(Subject.class)));
        when(mockedRepository.findById(1001L)).thenReturn(Optional.of(Mockito.mock(Subject.class)));
        subjectService.deleteById(1000L);
        subjectService.deleteById(1001L);
        assertEquals(new ArrayList<>(), subjectService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        when(mockedRepository.findById(123L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(SQLException.class, () -> subjectService.deleteById(123L));
        assertEquals("Subject with id=123 doesn't exist", thrown.getMessage());
    }
}