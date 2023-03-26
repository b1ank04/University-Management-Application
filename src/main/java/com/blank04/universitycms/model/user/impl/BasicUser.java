package com.blank04.universitycms.model.user.impl;

import com.blank04.universitycms.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.STRING)
public class BasicUser extends User {

    @Column(name = "user_type", insertable = false, updatable = false)
    @Getter
    private String userType;

    public boolean isAdmin() {
        return super.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
    }
}
