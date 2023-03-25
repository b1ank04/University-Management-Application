package com.blank04.universitycms.service.impl;

import com.blank04.universitycms.model.user.impl.BasicUser;
import com.blank04.universitycms.repository.StudentRepository;
import com.blank04.universitycms.repository.TeacherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public UserDetailsServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<? extends BasicUser> user = studentRepository.findStudentByUsername(username).isPresent() ?
                studentRepository.findStudentByUsername(username) : teacherRepository.findTeacherByUsername(username);
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