package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase
class FacultyServiceTest {

    @Mock
    FacultyRepository mockedRepository;

    @InjectMocks
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