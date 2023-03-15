package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.user.impl.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GroupService {
    Group save(Group group);
    Optional<Group> findById(Long id);
    List<Group> findAll();
    void deleteById(Long id) throws SQLException;
    List<Student> findRelatedStudents(Long groupId);
}
