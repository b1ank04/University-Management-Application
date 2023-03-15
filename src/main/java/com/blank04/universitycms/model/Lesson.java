package com.blank04.universitycms.model;

import lombok.*;

import java.sql.Date;
@Data
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
