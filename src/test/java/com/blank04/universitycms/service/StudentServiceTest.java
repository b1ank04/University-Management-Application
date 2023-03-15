package com.blank04.universitycms.service;

import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.repository.StudentRepository;
import com.blank04.universitycms.service.impl.StudentServiceImpl;
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

@SpringBootTest(classes = StudentServiceImpl.class)
class StudentServiceTest {

    @MockBean
    StudentRepository mockedRepository;

    @Autowired
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