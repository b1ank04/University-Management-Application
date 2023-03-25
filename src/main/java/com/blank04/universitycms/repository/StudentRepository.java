package com.blank04.universitycms.repository;

import com.blank04.universitycms.model.user.impl.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByUsername(String username);
}
