package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional(readOnly = true)
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        Optional<Subject> entity = findById(id);
        if (entity.isPresent()) {
            subjectRepository.deleteById(id);
        } else throw new SQLException("Subject with id="+ id + " doesn't exist");
    }
}
