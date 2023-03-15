package com.blank04.universitycms.database;

import com.blank04.universitycms.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!disableDataGeneration")
public class DatabaseFillerTrigger implements ApplicationRunner {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AudienceRepository audienceRepository;
    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;

    private final DatabaseFiller databaseFiller;

    public DatabaseFillerTrigger(GroupRepository groupRepository, StudentRepository studentRepository,
                                 SubjectRepository subjectRepository, AudienceRepository audienceRepository,
                                 FacultyRepository facultyRepository, TeacherRepository teacherRepository, DatabaseFiller databaseFiller) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.audienceRepository = audienceRepository;
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
        this.databaseFiller = databaseFiller;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean isDatabaseNotFullyInit =
                audienceRepository.count() < 1 &&
                        facultyRepository.count() < 1 &&
                        subjectRepository.count() < 1 &&
                        groupRepository.count() < 1 &&
                        studentRepository.count() < 1 &&
                        teacherRepository.count() < 1;

        if (isDatabaseNotFullyInit) {
            databaseFiller.fillDatabase();
        }
    }

}
