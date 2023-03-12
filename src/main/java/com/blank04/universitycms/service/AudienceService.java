package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.repository.AudienceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AudienceService {

    private final AudienceRepository audienceRepository;

    public AudienceService(AudienceRepository audienceRepository) {
        this.audienceRepository = audienceRepository;
    }

    @Transactional
    public Audience save(Audience audience) {
        return audienceRepository.save(audience);
    }

    @Transactional(readOnly = true)
    public Optional<Audience> findById(Long id) {
        return audienceRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Audience> findAll() {
        return audienceRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws SQLException {
        Optional<Audience> entity = findById(id);
        if (entity.isPresent()) {
            audienceRepository.deleteById(id);
        } else throw new SQLException("Audience with id="+ id + " doesn't exist");
    }
}
