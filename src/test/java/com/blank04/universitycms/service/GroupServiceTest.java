package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.repository.GroupRepository;
import com.blank04.universitycms.service.impl.GroupServiceImpl;
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
import static org.mockito.Mockito.when;

@SpringBootTest(classes = GroupServiceImpl.class)
class GroupServiceTest {

    @MockBean
    GroupRepository mockedRepository;

    @Autowired
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