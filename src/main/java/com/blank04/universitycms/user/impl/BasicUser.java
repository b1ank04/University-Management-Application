package com.blank04.universitycms.user.impl;

import com.blank04.universitycms.user.User;

public class BasicUser implements User {
    private Long id;
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
