package com.blank04.universitycms.repository;

import com.blank04.universitycms.model.user.impl.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {
    Optional<BasicUser> findByUsername(String username);
}