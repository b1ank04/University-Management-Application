package com.blank04.universitycms.faculty.unit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "audiences")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Audience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
