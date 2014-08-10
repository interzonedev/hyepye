package com.interzonedev.hyepye.service.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.base.Strings;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.command.user.GetUserByNameCommand;

@Named("hyepye.service.userDetailsService")
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

        if (Strings.isNullOrEmpty(username)) {
            throw new UsernameNotFoundException("The username must be set");
        }

        GetUserByNameCommand getUserByNameCommand = (GetUserByNameCommand) applicationContext.getBean(
                "hyepye.service.getUserByNameCommand", username);

        HyePyeResponse hyePyeResponse = getUserByNameCommand.execute();

        User user = hyePyeResponse.getUser();

        if ((null == user) || !user.isActive()) {
            String errorMessage = "Could not retrieve User with username " + username;
            log.debug("loadUserByUsername: " + errorMessage);
            throw new UsernameNotFoundException(errorMessage);
        }

        HyePyeUserDetails userDetails = new HyePyeUserDetails(user, true, true, true);

        log.debug("loadUserByUsername: End");

        return userDetails;

    }

}
