package com.blank04.universitycms.service;

import com.blank04.universitycms.model.user.impl.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Teacher save(Teacher teacher);
    Optional<Teacher> findById(Long id);
    List<Teacher> findAll();
    void deleteById(Long id) throws SQLException;
}
