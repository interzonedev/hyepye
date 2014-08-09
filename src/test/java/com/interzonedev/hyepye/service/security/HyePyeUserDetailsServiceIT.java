package com.interzonedev.hyepye.service.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.zankou.dataset.DataSet;

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

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameEmptyUsername() {

        log.debug("testLoadUserByUsernameEmptyUsername");

        hyePyeUserDetailsService.loadUserByUsername("");

    }

    @Test(expected = UsernameNotFoundException.class)
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testLoadUserByUsernameNonExistent() {

        log.debug("testLoadUserByUsernameNonExistent");

        hyePyeUserDetailsService.loadUserByUsername("this_user_does_not_exist");

    }

    @Test(expected = UsernameNotFoundException.class)
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testLoadUserByUsernameInactive() {

        log.debug("testLoadUserByUsernameInactive");

        hyePyeUserDetailsService.loadUserByUsername("cousit");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testLoadUserByUsernameValid() {

        log.debug("testLoadUserByUsernameValid: Start");

        User testUser = getTestUser(1L);

        UserDetails userDetails = hyePyeUserDetailsService.loadUserByUsername(testUser.getUsername());

        String roleName = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

        Assert.assertEquals(testUser.getUsername(), userDetails.getUsername());
        Assert.assertEquals(testUser.getPasswordHash(), userDetails.getPassword());
        Assert.assertEquals(1, userDetails.getAuthorities().size());
        Assert.assertEquals(roleName, testUser.getRole().getRoleName());
        Assert.assertTrue(userDetails.isAccountNonExpired());
        Assert.assertTrue(userDetails.isAccountNonLocked());
        Assert.assertTrue(userDetails.isCredentialsNonExpired());
        Assert.assertTrue(userDetails.isEnabled());

        log.debug("testLoadUserByUsernameValid: End");

    }

}
