package com.blank04.universitycms.database;

import com.blank04.universitycms.model.Lesson;
import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.model.user.Role;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.model.user.impl.Teacher;
import lombok.experimental.UtilityClass;
import org.apache.commons.text.RandomStringGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
public class DataGenerator {
    private static final Random random = new Random();

    private static final AtomicInteger userCount = new AtomicInteger(1);


    public static List<Student> createStudents(List<Group> groups) {
        List<String> firstNames = List.of("Artur", "Bogdan", "Vadim", "Ivan",
                "Dmitriy", "Egor", "Denis", "Anton", "Yaroslav", "Matvei",
                "Evgeniy", "Alla", "Maria", "Anastasia", "Polina", "Alisa",
                "Svetlana", "Julia", "Konstantin", "Victoria");
        List<String> lastNames = List.of("Shurko", "Prokopenko", "Ivanov(a)", "Svetlov(a)",
                "Taran", "Grachov(a)", "Kuzmenko", "Kravchenko", "Popov(a)", "Petrov(a)",
                "Smirnov(a)", "Sokolov(a)", "Golubev(a)", "Orlov(a)", "Antonov(a)", "Pavlov(a)",
                "Fedorov(a)", "Galkin(a)", "Gromov(a)", "Blohin(a)");
        Set<Student> students = new HashSet<>();
        while (students.size() < 200) {
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));
            Student student = new Student(null, firstName, lastName);
            student.setUsername("user_" + userCount.getAndIncrement());
            student.setPassword(randomPassword(10));
            student.addRole(Role.STUDENT);
            students.add(student);
        }

        return assignStudents(students.stream().toList(), groups);
    }

    private static List<Student> assignStudents(List<Student> students, List<Group> groups) {
        for (Group group : groups) {
            int amountOfStudentsInGroup = ThreadLocalRandom.current().nextInt(10, 31);
            for (int j = 0; j < amountOfStudentsInGroup; ++j) {
                students.get(ThreadLocalRandom.current().nextInt(0, 200)).setGroup(group);
            }
        }
        return students;
    }

    public static List<Group> createGroups(List<Faculty> faculties) {
        Set<String> groupNames = new HashSet<>();
        List<Group> groups = new ArrayList<>();
        while (groupNames.size() < 10) {
            char firstChar = (char) (random.nextInt(26) + 'a');
            char secondChar = (char) (random.nextInt(26) + 'a');
            String name = (String.valueOf(firstChar) + secondChar + "-" + (random.nextInt(99 - 10) + 10)).toUpperCase();
            groupNames.add(name);
        }
        for (String name : groupNames) {
            groups.add(new Group(null, name, faculties.get(random.nextInt(0,2))));
        }
        return groups;
    }

    public static List<Teacher> createTeachers(List<Subject> subjects) {
        List<String> firstNames = List.of("Artur", "Bogdan", "Vadim", "Ivan",
                "Dmitriy", "Egor", "Denis", "Anton", "Yaroslav", "Alla");
        List<String> lastNames = List.of("Shurko", "Prokopenko", "Ivanov(a)", "Svetlov(a)",
                "Taran", "Grachov(a)", "Kuzmenko", "Kravchenko", "Popov(a)", "Petrov(a)");
        Set<Teacher> teachers = new HashSet<>();
        int subjectIndex = 0;
        while (teachers.size() < 30) {
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));
            Subject subject = subjectIndex < 10 ? subjects.get(subjectIndex) : subjects.get(ThreadLocalRandom.current().nextInt(subjects.size()));
            Teacher teacher = new Teacher(null, firstName, lastName, subject);
            teacher.setUsername("user_" + userCount.getAndIncrement());
            teacher.setPassword(randomPassword(random.nextInt(8,13)));
            teacher.addRole(Role.TEACHER);
            if (random.nextBoolean()) teacher.addRole(Role.ADMIN);
            teachers.add(teacher);
            subjectIndex++;
        }
        for (Subject subject : subjects) {
            teachers.stream().findAny().orElseThrow().setSubject(subject);
        }
        List<Teacher> shuffleTeachers = new ArrayList<>(teachers.stream().toList());
        Collections.shuffle(shuffleTeachers);
        return shuffleTeachers;
    }

    public static List<Audience> createAudiences() {
        List<Audience> audiences = new ArrayList<>();
        while (audiences.size() < 20) {
            audiences.add(new Audience(null, random.nextInt(1, 4)));
        }
        return audiences;
    }

    public static List<Subject> createSubjects(List<Faculty> faculties) {
        List<String> subjects = new ArrayList<>(List.of("Mathematics", "Biology", "Physical Education",
                "Art", "Astronomy", "Physics", "Computer Science",
                "History", "Economics", "Philosophy"));
        Collections.shuffle(subjects);
        List<Subject> resultSubjects = new ArrayList<>();
        for (String name : subjects) {
            resultSubjects.add(new Subject(null, name, faculties.get(random.nextInt(0, faculties.size()))));
        }
        return resultSubjects;

    }

    public static List<Faculty> createFaculties() {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(null, "FFO"));
        faculties.add(new Faculty(null, "FIOT"));
        return faculties;
    }

    public static Map<LocalDate, List<Lesson>> createSchedule(List<Teacher> teachers, List<Subject> subjects) {
        Map<LocalDate, List<Lesson>> schedule = new HashMap<>();
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusMonths(1);
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            List<Lesson> studyDay = createStudyDay(teachers, subjects);
            for (Lesson lesson : studyDay) {
                lesson.setDate(Date.valueOf(date));
            }
            schedule.put(date, studyDay);
        }
        return schedule;
    }

    private static List<Lesson> createStudyDay(List<Teacher> teachers, List<Subject> subjects) {
        int amountOfLessons = 4;
        int startAudience = random.nextInt(10);
        List<Lesson> lessons = new ArrayList<>();
        for (int i = 1; i <= amountOfLessons; ++i) {
            for (int groupIndex = 1; groupIndex <= 10; ++groupIndex) {
                boolean groupHasLesson = random.nextBoolean();
                if (groupHasLesson) {
                    Lesson lesson = randomLesson(teachers, subjects);
                    lesson.setGroupId((long) groupIndex);
                    lesson.setAudienceId((long) startAudience + groupIndex);
                    lesson.setNumber(i);
                    lessons.add(lesson);
                }
            }
        }
        return lessons;
    }

    private static Lesson randomLesson(List<Teacher> teachers, List<Subject> subjects) {
        Long subjectId = subjects.get(ThreadLocalRandom.current().nextInt(subjects.size())).getId();
        Teacher matchingTeacher = teachers.stream()
                .filter(teacher -> Objects.equals(teacher.getSubject().getId(), subjectId))
                .findAny()
                .orElse(null);
        if (matchingTeacher != null) {
            return new Lesson(null, subjectId, null
                    , null, matchingTeacher.getId()
                    , null, null);
        } else throw new NoSuchElementException(String.format("Teacher with subject_id=%d doesn't exist", subjectId));
    }

    private static String randomPassword(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(65, 121)
                .build();
        return pwdGenerator.generate(length);
    }
}
