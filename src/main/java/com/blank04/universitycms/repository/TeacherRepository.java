package com.blank04.universitycms.repository;

import com.blank04.universitycms.model.user.impl.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findTeacherByUsername(String username);
}
