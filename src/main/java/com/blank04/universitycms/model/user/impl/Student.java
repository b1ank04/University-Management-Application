package com.blank04.universitycms.model.user.impl;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@NoArgsConstructor
@DiscriminatorValue("student")
public class Student extends BasicUser {

    @ManyToOne
    @JoinColumn(name = "group_id")
    @EqualsAndHashCode.Exclude
    @Setter
    @Getter
    @ToString.Exclude
    private Group group;

    public Student(Long id, String firstName, String lastName) {
        super.setId(id);
        super.setFirstName(firstName);
        super.setLastName(lastName);
    }

    public Student(Long id, String firstName, String lastName, Group group) {
        super.setId(id);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(getId(), student.getId()) && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(group, student.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroup(), getFirstName(), getLastName());
    }

}
