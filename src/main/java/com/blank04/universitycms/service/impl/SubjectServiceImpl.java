package com.blank04.universitycms.service.impl;

import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.repository.SubjectRepository;
import com.blank04.universitycms.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws SQLException {
        Optional<Subject> entity = findById(id);
        if (entity.isPresent()) {
            subjectRepository.deleteById(id);
        } else throw new SQLException("Subject with id="+ id + " doesn't exist");
    }
}
