package com.blank04.universitycms;

import com.blank04.universitycms.faculty.unit.Lesson;
import com.blank04.universitycms.user.User;
import com.blank04.universitycms.user.impl.Student;
import com.blank04.universitycms.user.impl.Teacher;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class TimeTable {

    @ElementCollection
    @CollectionTable(name = "lessons")
    @Getter
    private static Map<LocalDate, List<Lesson>> schedule;

    public List<Lesson> getUserSchedule(User user, LocalDate date) {
        List<Lesson> lessons = schedule.get(date);
        if (user instanceof Teacher teacher) {
            return lessons.stream()
                    .filter(lesson -> Objects.equals(lesson.getTeacherId(), teacher.getId()))
                    .toList();
        } else if (user instanceof Student student) {
            return lessons.stream()
                    .filter(lesson -> Objects.equals(lesson.getGroupId(), student.getGroupId()))
                    .toList();
        } else throw new IllegalArgumentException("No lessons for such type of user");
    }

    public static void setSchedule(Map<LocalDate, List<Lesson>> schedule) {
        TimeTable.schedule = schedule;
    }
}
