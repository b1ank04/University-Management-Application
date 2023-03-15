package com.blank04.universitycms.database;

import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest(includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {AudienceRepository.class, FacultyRepository.class, DatabaseFiller.class,
        GroupRepository.class, StudentRepository.class, SubjectRepository.class, TeacherRepository.class})})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class DatabaseFillerTest {

    DatabaseFiller databaseFiller;
    AudienceRepository audienceRepository;
    FacultyRepository facultyRepository;
    GroupRepository groupRepository;
    StudentRepository studentRepository;
    SubjectRepository subjectRepository;
    TeacherRepository teacherRepository;

    @Autowired
    public DatabaseFillerTest(DatabaseFiller databaseFiller, AudienceRepository audienceRepository,
                              FacultyRepository facultyRepository, GroupRepository groupRepository,
                              StudentRepository studentRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.databaseFiller = databaseFiller;
        this.audienceRepository = audienceRepository;
        this.facultyRepository = facultyRepository;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Test
    void run() throws SQLException, IOException {
        long dbCount = audienceRepository.count() + facultyRepository.count()
                + groupRepository.count() + studentRepository.count()
                + subjectRepository.count() + teacherRepository.count();

        assertEquals(0, dbCount);
        databaseFiller.fillDatabase();
        verify(databaseFiller, times(1)).fillDatabase();
    }
}