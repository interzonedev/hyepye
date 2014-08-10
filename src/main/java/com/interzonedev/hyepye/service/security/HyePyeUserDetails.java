package com.interzonedev.hyepye.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.interzonedev.hyepye.model.User;

public class HyePyeUserDetails extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = -8366776020948463559L;

    private final User user;

    public HyePyeUserDetails(User user, boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked) {
        super(user.getUsername(), user.getPasswordHash(), user.isActive(), accountNonExpired, credentialsNonExpired,
                accountNonLocked, getAuthoritiesFromUser(user));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private static Collection<? extends GrantedAuthority> getAuthoritiesFromUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return authorities;
    }

}
