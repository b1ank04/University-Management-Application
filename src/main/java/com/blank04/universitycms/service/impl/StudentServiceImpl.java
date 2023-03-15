package com.blank04.universitycms.service.impl;

import com.blank04.universitycms.model.user.impl.Student;
import com.blank04.universitycms.repository.StudentRepository;
import com.blank04.universitycms.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws SQLException {
        Optional<Student> entity = findById(id);
        if (entity.isPresent()) {
            studentRepository.deleteById(id);
        } else throw new SQLException("Student with id="+ id + " doesn't exist");
    }
}
