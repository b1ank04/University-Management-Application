package com.blank04.universitycms.repository;

import com.blank04.universitycms.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
