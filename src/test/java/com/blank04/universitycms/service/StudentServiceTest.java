package com.blank04.universitycms.service;

import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.repository.StudentRepository;
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
class StudentServiceTest {

    @Mock
    StudentRepository mockedRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    void shouldDeleteById() throws SQLException {
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(Mockito.mock(Student.class)));
        when(mockedRepository.findById(1001L)).thenReturn(Optional.of(Mockito.mock(Student.class)));
        studentService.deleteById(1000L);
        studentService.deleteById(1001L);
        assertEquals(new ArrayList<>(), studentService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        when(mockedRepository.findById(123L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(SQLException.class, () -> studentService.deleteById(123L));
        assertEquals("Student with id=123 doesn't exist", thrown.getMessage());
    }
}