package com.blank04.universitycms.service;

import com.blank04.universitycms.model.user.impl.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(Student student);
    Optional<Student> findById(Long id);
    List<Student> findAll();
    void deleteById(Long id) throws SQLException;
}
