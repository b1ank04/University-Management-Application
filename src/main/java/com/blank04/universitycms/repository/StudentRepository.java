package com.blank04.universitycms.repository;

import com.blank04.universitycms.model.user.impl.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
