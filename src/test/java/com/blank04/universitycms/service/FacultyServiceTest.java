package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
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

@DataJpaTest(includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {FacultyService.class})})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)

class FacultyServiceTest {

    @Autowired
    FacultyService facultyService;

    @Test
    void shouldDeleteById() throws SQLException {
        facultyService.deleteById(1000L);
        facultyService.deleteById(1001L);
        assertEquals(new ArrayList<>(), facultyService.findAll());
    }

    @Test
    void shouldNotDeleteById() {
        Exception thrown = assertThrows(SQLException.class, () -> facultyService.deleteById(1L));
        assertEquals("Faculty with id=1 doesn't exist", thrown.getMessage());
    }

    @Test
    void findRelatedSubjects() {
        List<Subject> subjects = facultyService.findRelatedSubjects(1000L);
        assertEquals(new Subject(1000L, "TEST"), subjects.get(0));
    }

    @Test
    void shouldNotFindRelatedSubjects() {
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> facultyService.findRelatedSubjects(123L));
        assertEquals("Faculty with id=123 doesn't exist", thrown.getMessage());
    }

    @Test
    void shouldFindRelatedGroups() {
        List<Group> groups = facultyService.findRelatedGroups(1000L);
        assertEquals(new Group(1000L, "TEST", facultyService.findById(1000L).get()), groups.get(0));
    }

    @Test
    void shouldNotFindRelatedGroups() {
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> facultyService.findRelatedGroups(123L));
        assertEquals("Faculty with id=123 doesn't exist", thrown.getMessage());
    }
}