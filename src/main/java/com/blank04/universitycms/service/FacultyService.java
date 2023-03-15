package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Faculty save(Faculty faculty);
    Optional<Faculty> findById(Long id);
    List<Faculty> findAll();
    void deleteById(Long id) throws SQLException;
    List<Subject> findRelatedSubjects(Long id);
    List<Group> findRelatedGroups(Long id);
}
