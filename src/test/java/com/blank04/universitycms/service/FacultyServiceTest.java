package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.repository.FacultyRepository;
import com.blank04.universitycms.service.impl.FacultyServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FacultyServiceImpl.class)
class FacultyServiceTest {

    @MockBean
    FacultyRepository mockedRepository;

    @Autowired
    FacultyService facultyService;

    @Test
    void shouldDeleteById() throws SQLException {
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(Mockito.mock(Faculty.class)));
        when(mockedRepository.findById(1001L)).thenReturn(Optional.of(Mockito.mock(Faculty.class)));
        facultyService.deleteById(1000L);
        facultyService.deleteById(1001L);
        assertEquals(new ArrayList<>(), facultyService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        Exception thrown = assertThrows(SQLException.class, () -> facultyService.deleteById(1L));
        verify(mockedRepository).findById(1L);
        assertEquals("Faculty with id=1 doesn't exist", thrown.getMessage());
    }

    @Test
    void findRelatedSubjects() {
        Faculty faculty = new Faculty();
        faculty.addSubject(new Subject(1000L, "TEST"));
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(faculty));
        List<Subject> subjects = facultyService.findRelatedSubjects(1000L);
        assertEquals(new Subject(1000L, "TEST"), subjects.get(0));
    }

    @Test
    void shouldNotFindRelatedSubjects() {
        when(mockedRepository.findById(123L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> facultyService.findRelatedSubjects(123L));
        assertEquals("Faculty with id=123 doesn't exist", thrown.getMessage());
    }

    @Test
    void shouldFindRelatedGroups() {
        Faculty faculty = new Faculty();
        faculty.addGroup(new Group(1000L, "TEST", faculty));
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(faculty));
        List<Group> groups = facultyService.findRelatedGroups(1000L);
        assertEquals(new Group(1000L, "TEST", faculty), groups.get(0));
    }

    @Test
    void shouldNotFindRelatedGroups() {
        when(mockedRepository.findById(123L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> facultyService.findRelatedGroups(123L));
        assertEquals("Faculty with id=123 doesn't exist", thrown.getMessage());
    }
}