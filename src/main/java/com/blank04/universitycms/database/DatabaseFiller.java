package com.blank04.universitycms.database;

import com.blank04.universitycms.model.TimeTable;
import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.model.user.impl.Teacher;
import com.blank04.universitycms.repository.*;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Getter
public class DatabaseFiller {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AudienceRepository audienceRepository;
    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;

    public DatabaseFiller(GroupRepository groupRepository, StudentRepository studentRepository,
                          SubjectRepository subjectRepository, AudienceRepository audienceRepository,
                          FacultyRepository facultyRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.audienceRepository = audienceRepository;
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public void fillDatabase() {
        List<Faculty> faculties = DataGenerator.createFaculties();
        List<Subject> subjects = DataGenerator.createSubjects(faculties);
        List<Audience> audiences = DataGenerator.createAudiences();
        List<Teacher> teachers = DataGenerator.createTeachers(subjects);
        List<Group> groups = DataGenerator.createGroups(faculties);
        List<Student> students = DataGenerator.createStudents(groups);

        teacherRepository.deleteAll();
        studentRepository.deleteAll();
        groupRepository.deleteAll();
        subjectRepository.deleteAll();
        facultyRepository.deleteAll();
        audienceRepository.deleteAll();


        fillAudiences(audiences);
        fillFaculties(faculties);
        fillSubjects(subjects);
        fillGroups(groups);
        fillStudents(students);
        fillTeachers(teachers);

        TimeTable.setSchedule(DataGenerator.createSchedule(teachers, subjects));
    }

    public List<Group> fillGroups(List<Group> groups) {
        return groupRepository.saveAll(groups);
    }

    private List<Student> fillStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    public List<Subject> fillSubjects(List<Subject> subjects) {
        return subjectRepository.saveAll(subjects);
    }

    private List<Audience> fillAudiences(List<Audience> audiences) {
        return audienceRepository.saveAll(audiences);
    }

    public List<Faculty> fillFaculties(List<Faculty> faculties) {
        return facultyRepository.saveAll(faculties);
    }

    private List<Teacher> fillTeachers(List<Teacher> teachers) {
        return teacherRepository.saveAll(teachers);
    }
}
