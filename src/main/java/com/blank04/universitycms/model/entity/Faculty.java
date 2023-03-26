package com.blank04.universitycms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculties")
@Getter
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Faculty(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private final Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private final Set<Group> groups = new HashSet<>();

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
    public void addGroup(Group group) {
        groups.add(group);
    }
}
