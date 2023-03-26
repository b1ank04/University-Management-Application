package com.blank04.universitycms.model.user.impl;

import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("teacher")
public class Teacher extends BasicUser {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ToString.Exclude
    @Getter
    @Setter
    private Subject subject;

    public Teacher(Long id, String firstName, String lastName, Subject subject) {
        super.setId(id);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.subject = subject;
    }
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
