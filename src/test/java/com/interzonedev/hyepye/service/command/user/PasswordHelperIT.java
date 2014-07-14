package com.interzonedev.hyepye.service.command.user;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyepyeAbstractIT;

/**
 * Integration tests for {@link PasswordHelper}.
 * 
 * @author mmarkarian
 */
public class PasswordHelperIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(PasswordHelperIT.class);

    @Inject
    @Named("hyepye.service.passwordHelper")
    private PasswordHelper passwordHelper;

    @Test
    public void testGeneratePasswordSeed() {

        log.debug("testGeneratePasswordSeed: Start");

        String passwordSeed = passwordHelper.generatePasswordSeed();

        Assert.assertEquals(10, passwordSeed.length());

        log.debug("testGeneratePasswordSeed: End");

    }

    @Test
    public void testGeneratePasswordHash() throws UnsupportedEncodingException {

        log.debug("testGeneratePasswordHash: Start");

        String password = "testpass";

        String passwordSeed = passwordHelper.generatePasswordSeed();

        String passwordHash1 = passwordHelper.generatePasswordHash(password, passwordSeed);
        String passwordHash2 = passwordHelper.generatePasswordHash(password, passwordSeed);

        Assert.assertEquals(64, passwordHash1.length());
        Assert.assertEquals(64, passwordHash2.length());
        Assert.assertEquals(passwordHash1, passwordHash2);

        log.debug("testGeneratePasswordHash: End");

    }

}
