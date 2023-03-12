package com.blank04.universitycms.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Faculty faculty;
}
