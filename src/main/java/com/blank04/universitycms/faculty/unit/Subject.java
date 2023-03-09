package com.blank04.universitycms.faculty.unit;

import com.blank04.universitycms.faculty.Faculty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
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
    private Faculty faculty;
}
