package com.blank04.universitycms.service;

import com.blank04.universitycms.model.user.impl.Teacher;
import com.blank04.universitycms.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase
class TeacherServiceTest {

    @Mock
    TeacherRepository mockedRepository;

    @InjectMocks
    TeacherService teacherService;

    @Test
    void shouldDeleteById() throws SQLException {
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(Mockito.mock(Teacher.class)));
        when(mockedRepository.findById(1001L)).thenReturn(Optional.of(Mockito.mock(Teacher.class)));
        teacherService.deleteById(1000L);
        teacherService.deleteById(1001L);
        assertEquals(new ArrayList<>(), teacherService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        when(mockedRepository.findById(123L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(SQLException.class, () -> teacherService.deleteById(123L));
        assertEquals("Teacher with id=123 doesn't exist", thrown.getMessage());
    }
}