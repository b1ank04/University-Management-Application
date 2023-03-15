package com.blank04.universitycms.model.user.impl;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher extends BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    private Subject subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher teacher)) return false;
        return Objects.equals(getId(), teacher.getId()) && Objects.equals(subject, teacher.getSubject()) && Objects.equals(getFirstName(), teacher.getFirstName()) && Objects.equals(getLastName(), teacher.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), subject, getFirstName(), getLastName());
    }
}
