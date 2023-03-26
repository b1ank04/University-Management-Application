package com.blank04.universitycms.repository;

import com.blank04.universitycms.model.user.impl.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
