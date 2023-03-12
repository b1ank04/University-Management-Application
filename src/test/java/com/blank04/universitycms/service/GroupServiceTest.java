package com.blank04.universitycms.service;

import com.blank04.universitycms.model.user.impl.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {GroupService.class})})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class GroupServiceTest {

    @Autowired
    GroupService groupService;

    @Test
    void shouldDeleteById() throws SQLException {
        groupService.deleteById(1000L);
        groupService.deleteById(1001L);
        assertEquals(new ArrayList<>(), groupService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        Exception thrown = assertThrows(SQLException.class, () -> groupService.deleteById(123L));
        assertEquals("Group with id=123 doesn't exist", thrown.getMessage());
    }

    @Test
    void shouldFindRelatedStudents() {
        List<Student> studentList = groupService.findRelatedStudents(1000L);
        assertEquals(new Student(1000L, 1000L, "John", "Doe"), studentList.get(0));
    }

    @Test
    void shouldNotFindRelatedStudents() {
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> groupService.findRelatedStudents(123L));
        assertEquals("Group with id=123 doesn't exist", thrown.getMessage());
    }
}