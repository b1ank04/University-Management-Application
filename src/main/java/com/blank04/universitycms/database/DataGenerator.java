package com.blank04.universitycms.database;

import com.blank04.universitycms.faculty.Faculty;
import com.blank04.universitycms.faculty.unit.Audience;
import com.blank04.universitycms.faculty.unit.Group;
import com.blank04.universitycms.faculty.unit.Lesson;
import com.blank04.universitycms.faculty.unit.Subject;
import com.blank04.universitycms.user.impl.Student;
import com.blank04.universitycms.user.impl.Teacher;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    private static final Random random = new Random();

    private DataGenerator() {
        throw new IllegalStateException("Utility class");
    }
    public static List<Student> createStudents() {
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
            Student student = new Student(null, null, firstName, lastName);
            students.add(student);
        }

        return assignStudents(students.stream().toList());
    }

    private static List<Student> assignStudents(List<Student> students) {
        for (int i = 1; i <= 10; ++i) {
            int amountOfStudentsInGroup = ThreadLocalRandom.current().nextInt(10, 31);
            for (int j = 0; j < amountOfStudentsInGroup; ++j) {
                students.get(ThreadLocalRandom.current().nextInt(0, 200)).setGroupId((long) i);
            }
        }
        return students;
    }

    public static List<Group> createGroups(List<Faculty> faculties) {
        Set<String> groupNames = new HashSet<>();
        List<Group> groups = new ArrayList<>();
        while (groupNames.size() < 10) {
            char firstChar = (char)(random.nextInt(26) + 'a');
            char secondChar = (char)(random.nextInt(26) + 'a');
            String name = (String.valueOf(firstChar) + secondChar + "-" + (random.nextInt(99-10)+10)).toUpperCase();
            groupNames.add(name);
        }
        for (String name : groupNames) {
            groups.add(new Group(null, name, faculties.get(random.nextInt(2))));
        }
        return groups;
    }

    public static List<Teacher> createTeachers() {
        List<String> firstNames = List.of("Artur", "Bogdan", "Vadim", "Ivan",
                "Dmitriy", "Egor", "Denis", "Anton", "Yaroslav", "Alla");
        List<String> lastNames = List.of("Shurko", "Prokopenko", "Ivanov(a)", "Svetlov(a)",
                "Taran", "Grachov(a)", "Kuzmenko", "Kravchenko", "Popov(a)", "Petrov(a)");
        Set<Teacher> teachers = new HashSet<>();
        while (teachers.size() < 10) {
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));
            Teacher teacher = new Teacher(null,random.nextLong(1, 11), firstName, lastName);
            teachers.add(teacher);
        }
        return teachers.stream().toList();
    }

    public static List<Audience> createAudiences() {
        Set<Audience> audiences = new HashSet<>();
        while (audiences.size() < 20) {
            audiences.add(new Audience(random.nextLong(200)));
        }
        return audiences.stream().toList();
    }

    public static List<Subject> createSubjects() {
        List<String> subjects = new ArrayList<>(List.of("Mathematics", "Biology", "Physical Education",
                "Art", "Astronomy", "Physics", "Computer Science",
                "History", "Economics", "Philosophy"));
        Collections.shuffle(subjects);
        Set<Subject> resultSubjects = new HashSet<>();
        for (String name : subjects) {
            resultSubjects.add(new Subject(null, name));
        }
        return resultSubjects.stream().toList();

    }

    public static List<Faculty> createFaculties(List<Subject> subjects) {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(null, "FFO"));
        faculties.add(new Faculty(null, "FIOT"));
        for (int i = 0; i < 5; ++i) {
            faculties.get(0).addSubject(subjects.get(i));
        }
        for(int i = 5; i < 10; ++i) {
            faculties.get(1).addSubject(subjects.get(i));
        }
        return faculties;
    }

    public static Map<LocalDate, List<Lesson>> createSchedule(List<Teacher> teachers) {
        Map<LocalDate, List<Lesson>> schedule = new HashMap<>();
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusMonths(1);
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            List<Lesson> studyDay = createStudyDay(teachers);
            for (Lesson lesson : studyDay) {
                lesson.setDate(Date.valueOf(date));
            }
            schedule.put(date, studyDay);
        }
        return schedule;
    }

    private static List<Lesson> createStudyDay(List<Teacher> teachers) {
        int amountOfLessons = 4;
        int startAudience = random.nextInt(10);
        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < amountOfLessons; ++i) {
            for (int groupIndex = 1; groupIndex <= 10; ++groupIndex) {
                boolean groupHasLesson = random.nextBoolean();
                if (groupHasLesson) {
                    Lesson lesson = randomLesson(teachers);
                    lesson.setGroupId((long) groupIndex);
                    lesson.setAudienceId((long) startAudience + groupIndex);
                    lessons.add(lesson);
                }
            }
        }
        return lessons;
    }

    private static Lesson randomLesson(List<Teacher> teachers) {
        Long subjectId = random.nextLong(11);
        Teacher teacher = new Teacher();
        boolean teacherNotFound = true;
        while (teacherNotFound) {
            Teacher temporalTeacher = teachers.get(random.nextInt(0,10));
            if (Objects.equals(temporalTeacher.getSubjectId(), subjectId)) {
                teacher = temporalTeacher;
                teacherNotFound = false;
            }
        }
        return new Lesson(null, subjectId, null
                ,null, teacher.getId()
                , null, null);
    }
}
