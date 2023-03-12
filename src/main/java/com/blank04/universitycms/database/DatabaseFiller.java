package com.blank04.universitycms.database;

import com.blank04.universitycms.model.TimeTable;
import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.model.user.impl.Teacher;
import com.blank04.universitycms.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseFiller implements ApplicationRunner {
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

    @Override
    public void run(ApplicationArguments args) {
        List<Subject> subjects = DataGenerator.createSubjects();
        List<Faculty> faculties = DataGenerator.createFaculties(subjects);
        List<Audience> audiences = DataGenerator.createAudiences();
        List<Teacher> teachers = DataGenerator.createTeachers();
        List<Group> groups = DataGenerator.createGroups(faculties);
        List<Student> students = DataGenerator.createStudents();

        int audiencesCount = audienceRepository.findAll().size();
        int facultiesCount = facultyRepository.findAll().size();
        int subjectsCount = subjectRepository.findAll().size();
        int groupsCount = groupRepository.findAll().size();
        int studentsCount = studentRepository.findAll().size();
        int teachersCount = teacherRepository.findAll().size();

        if (audiencesCount < 1) fillAudiences(audiences);
        if (facultiesCount < 1) fillFaculties(faculties);
        if (subjectsCount < 1) fillSubjects(subjects);
        if (groupsCount < 1) fillGroups(groups);
        if (studentsCount < 1) fillStudents(students);
        if (teachersCount < 1) fillTeachers(teachers);

        TimeTable.setSchedule(DataGenerator.createSchedule(teacherRepository.findAll()));
    }

    private void fillGroups(List<Group> groups) {
        groupRepository.saveAll(groups);
    }
    private void fillStudents(List<Student> students) {
        studentRepository.saveAll(students);
    }
    private void fillSubjects(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
    }
    private void fillAudiences(List<Audience> audiences) {
        audienceRepository.saveAll(audiences);
    }
    private void fillFaculties(List<Faculty> faculties) {
        facultyRepository.saveAll(faculties);
    }
    private void fillTeachers(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
    }
}
