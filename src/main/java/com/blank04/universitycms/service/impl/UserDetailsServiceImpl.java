package com.blank04.universitycms.service.impl;

import com.blank04.universitycms.model.user.User;
import com.blank04.universitycms.repository.BasicUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final BasicUserRepository basicUserRepository;

    public UserDetailsServiceImpl(BasicUserRepository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<? extends User> user = basicUserRepository.findByUsername(username);
        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getUsername())
                    .password(user.get().getPassword())
                    .authorities(user.get().getAuthorities())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}