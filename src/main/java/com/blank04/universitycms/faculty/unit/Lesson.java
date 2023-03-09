package com.blank04.universitycms.faculty.unit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/*@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "audience_id")
    private Long audienceId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "date")
    private Date date;

    @Column(name = "number")
    private Integer number;
}

 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    private Long id;

    private Long subjectId;

    private Long audienceId;

    private Long groupId;

    private Long teacherId;

    private Date date;

    private Integer number;
}
