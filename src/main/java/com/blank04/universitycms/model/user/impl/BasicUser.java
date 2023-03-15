package com.blank04.universitycms.model.user.impl;

import com.blank04.universitycms.model.user.User;

public class BasicUser implements User {
    private Long id;

    @Override
    public Long getId() {
        return id;
    }
}
