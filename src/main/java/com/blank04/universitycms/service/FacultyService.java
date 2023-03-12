package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.model.entity.Subject;
import com.blank04.universitycms.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Transactional
    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Transactional(readOnly = true)
    public Optional<Faculty> findById(Long id) {
        return facultyRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        Optional<Faculty> entity = findById(id);
        if (entity.isPresent()) {
            facultyRepository.deleteById(id);
        } else throw new SQLException("Faculty with id="+ id + " doesn't exist");
    }

    @Transactional(readOnly = true)
    public List<Subject> findRelatedSubjects(Long id) {
        Optional<Faculty> faculty = findById(id);
        if (faculty.isPresent()) {
            return faculty.get().getSubjects().stream().toList();
        } else throw new IllegalArgumentException(String.format("Faculty with id=%d doesn't exist", id));
    }

    @Transactional(readOnly = true)
    public List<Group> findRelatedGroups(Long id) {
        Optional<Faculty> faculty = findById(id);
        if (faculty.isPresent()) {
            return faculty.get().getGroups().stream().toList();
        } else throw new IllegalArgumentException(String.format("Faculty with id=%d doesn't exist", id));
    }
}
