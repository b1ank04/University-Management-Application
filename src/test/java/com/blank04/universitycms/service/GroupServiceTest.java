package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.repository.GroupRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase
class GroupServiceTest {

    @Mock
    GroupRepository mockedRepository;

    @InjectMocks
    GroupService groupService;

    @Test
    void shouldDeleteById() throws SQLException {
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(Mockito.mock(Group.class)));
        when(mockedRepository.findById(1001L)).thenReturn(Optional.of(Mockito.mock(Group.class)));
        groupService.deleteById(1000L);
        groupService.deleteById(1001L);
        assertEquals(new ArrayList<>(), groupService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        when(mockedRepository.findById(123L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(SQLException.class, () -> groupService.deleteById(123L));
        assertEquals("Group with id=123 doesn't exist", thrown.getMessage());
    }

    @Test
    void shouldFindRelatedStudents() {
        Group group = new Group(1000L, null, null);
        group.addStudent(new Student(1000L, "John", "Doe", group));
        when(mockedRepository.findById(1000L)).thenReturn(Optional.of(group));
        List<Student> studentList = groupService.findRelatedStudents(1000L);
        assertEquals(new Student(1000L, "John", "Doe", group), studentList.get(0));
    }

    @Test
    void shouldNotFindRelatedStudents() {
        when(mockedRepository.findById(123L)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> groupService.findRelatedStudents(123L));
        assertEquals("Group with id=123 doesn't exist", thrown.getMessage());
    }
}