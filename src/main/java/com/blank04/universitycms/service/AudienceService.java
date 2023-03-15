package com.blank04.universitycms.service;

import com.blank04.universitycms.model.entity.Audience;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AudienceService {
    Audience save(Audience audience);
    Optional<Audience> findById(Long id);
    List<Audience> findAll();
    void deleteById(Long id) throws SQLException;
}
