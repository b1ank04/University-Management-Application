package com.blank04.universitycms;

import com.blank04.universitycms.user.impl.Teacher;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;

import java.util.Set;

public class University {

    @ElementCollection
    @CollectionTable(name = "teachers")
    private Set<Teacher> teachers;

    @ElementCollection
    @CollectionTable(name = "students")
    private Set<Teacher> students;

    @ElementCollection
    @CollectionTable(name = "faculties")
    private Set<Teacher> faculties;

    @ElementCollection
    @CollectionTable(name = "audiences")
    private Set<Teacher> audiences;

}
