package com.blank04.universitycms.service.impl;

import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.repository.AudienceRepository;
import com.blank04.universitycms.service.AudienceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AudienceServiceImpl implements AudienceService {

    private final AudienceRepository audienceRepository;

    public AudienceServiceImpl(AudienceRepository audienceRepository) {
        this.audienceRepository = audienceRepository;
    }

    @Transactional
    @Override
    public Audience save(Audience audience) {
        return audienceRepository.save(audience);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Audience> findById(Long id) {
        return audienceRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Audience> findAll() {
        return audienceRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws SQLException {
        Optional<Audience> entity = findById(id);
        if (entity.isPresent()) {
            audienceRepository.deleteById(id);
        } else throw new SQLException("Audience with id="+ id + " doesn't exist");
    }
}
