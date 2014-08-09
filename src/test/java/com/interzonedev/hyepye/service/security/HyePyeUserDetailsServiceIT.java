package com.interzonedev.hyepye.service.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.interzonedev.hyepye.HyepyeAbstractIT;

public class HyePyeUserDetailsServiceIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(HyePyeUserDetailsServiceIT.class);

    @Inject
    @Named("hyepye.service.userDetailsService")
    private HyePyeUserDetailsService hyePyeUserDetailsService;

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNullUsername() {

        log.debug("testLoadUserByUsernameNullUsername");

        hyePyeUserDetailsService.loadUserByUsername(null);

    }

    // TODO - Complete test implementation.

}
