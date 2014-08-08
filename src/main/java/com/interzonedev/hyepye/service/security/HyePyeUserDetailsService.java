package com.interzonedev.hyepye.service.security;

import java.util.*;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.command.user.GetUserByNameCommand;

public class HyePyeUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(HyePyeUserDetailsService.class);

    private final ApplicationContext applicationContext;

    @Inject
    public HyePyeUserDetailsService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("loadUserByUsername: Start - username = " + username);

        GetUserByNameCommand getUserByNameCommand = (GetUserByNameCommand) applicationContext.getBean(
                "hyepye.service.getUserByNameCommand", username);

        HyePyeResponse hyePyeResponse = getUserByNameCommand.execute();

        User user = hyePyeResponse.getUser();

        if (null == user) {
            String errorMessage = "Could not retrieve User with username " + username;
            log.debug("loadUserByUsername: " + errorMessage);
            throw new UsernameNotFoundException(errorMessage);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPasswordHash(), authorities);

        log.debug("loadUserByUsername: End");

        return userDetails;

    }

}
