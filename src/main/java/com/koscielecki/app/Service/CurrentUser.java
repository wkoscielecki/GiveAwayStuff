package com.koscielecki.app.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final com.koscielecki.app.Entity.User user;
    public CurrentUser(String username, String password, Collection<?
            extends GrantedAuthority> authorities,
                       com.koscielecki.app.Entity.User user) {
        super(username, password, authorities); this.user = user;
    }
    public com.koscielecki.app.Entity.User getUser() {return user;}
}
