package com.blank04.universitycms.service.impl;

import com.blank04.universitycms.model.user.impl.Teacher;
import com.blank04.universitycms.repository.TeacherRepository;
import com.blank04.universitycms.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws SQLException {
        Optional<Teacher> entity = findById(id);
        if (entity.isPresent()) {
            teacherRepository.deleteById(id);
        } else throw new SQLException("Teacher with id="+ id + " doesn't exist");
    }
}
