package com.blank04.universitycms.database;

import com.blank04.universitycms.TimeTable;
import com.blank04.universitycms.faculty.Faculty;
import com.blank04.universitycms.faculty.unit.Audience;
import com.blank04.universitycms.faculty.unit.Group;
import com.blank04.universitycms.faculty.unit.Subject;
import com.blank04.universitycms.user.impl.Student;
import com.blank04.universitycms.user.impl.Teacher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseService implements ApplicationRunner {
    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_GROUPS = "INSERT INTO groups (faculty_id, name) values (?,?)";
    private static final String INSERT_STUDENTS = "INSERT INTO students (group_id, first_name, last_name) values (?,?,?)";
    private static final String INSERT_SUBJECTS = "INSERT INTO subjects (faculty_id, name) values (?,?)";
    private static final String INSERT_AUDIENCES = "INSERT INTO audiences (id) values (?)";
    private static final String INSERT_FACULTIES = "INSERT INTO faculties (name) values (?)";
    private static final String INSERT_TEACHERS = "INSERT INTO teachers (subject_id, first_name, last_name) values (?,?,?)";

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Subject> subjects = DataGenerator.createSubjects();
        List<Faculty> faculties = DataGenerator.createFaculties(subjects);
        List<Audience> audiences = DataGenerator.createAudiences();
        List<Teacher> teachers = DataGenerator.createTeachers();
        List<Group> groups = DataGenerator.createGroups(faculties);
        List<Student> students = DataGenerator.createStudents();
        TimeTable.setSchedule(DataGenerator.createSchedule(teachers));
        fillFaculties(faculties);
        fillSubjects(subjects);
        fillGroups(groups);
        fillAudiences(audiences);
        fillStudents(students);
        fillTeachers(teachers);
    }

    private void fillGroups(List<Group> groups) {
        for (Group group : groups) {
            jdbcTemplate.update(INSERT_GROUPS, group.getFaculty().getId(), group.getName());
        }
    }

    private void fillStudents(List<Student> students) {
        for (Student student : students) {
            jdbcTemplate.update(INSERT_STUDENTS, student.getGroupId(), student.getFirstName(), student.getLastName());
        }
    }

    private void fillSubjects(List<Subject> subjects) {
        for (Subject subject : subjects) {
            jdbcTemplate.update(INSERT_SUBJECTS, subject.getFaculty().getId(), subject.getName());
        }
    }

    private void fillAudiences(List<Audience> audiences) {
        for (Audience audience : audiences) {
            jdbcTemplate.update(INSERT_AUDIENCES, audience.getId());
        }
    }

    private void fillFaculties(List<Faculty> faculties) {
        for (Faculty faculty : faculties) {
            jdbcTemplate.update(INSERT_FACULTIES, faculty.getName());
        }
    }
    private void fillTeachers(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            jdbcTemplate.update(INSERT_TEACHERS, teacher.getSubjectId(), teacher.getFirstName(), teacher.getLastName());
        }
    }
}
