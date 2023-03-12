package com.blank04.universitycms.model;

import com.blank04.universitycms.model.user.User;
import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.model.user.impl.Teacher;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TimeTable {

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
        } else throw new IllegalArgumentException("User should be logged in as student or teacher");
    }

    public static void setSchedule(Map<LocalDate, List<Lesson>> schedule) {
        TimeTable.schedule = schedule;
    }
}
