package com.blank04.universitycms.user.impl;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student extends BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
