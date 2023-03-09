package com.blank04.universitycms.user.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher extends BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
