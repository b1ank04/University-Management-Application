package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Subject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Subject save(Subject subject);
    Optional<Subject> findById(Long id);

    List<Subject> findAll();
    void deleteById(Long id) throws SQLException;
}
