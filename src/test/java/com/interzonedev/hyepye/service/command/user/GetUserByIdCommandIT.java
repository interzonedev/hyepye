package com.interzonedev.hyepye.service.command.user;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link GetUserByIdCommand}.
 * 
 * @author mmarkarian
 */
public class GetUserByIdCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(GetUserByIdCommandIT.class);

    @Inject
    private ApplicationContext applicationContext;

    /**
     * The validation error message should be set on the response for a non positive ID.
     */
    @Test
    public void testGetUserByIdNotPositive() {

        log.debug("testGetUserByIdNotPositive: Start");

        GetUserByIdCommand getUserByIdCommand = (GetUserByIdCommand) applicationContext.getBean(
                "hyepye.service.getUserByIdCommand", 0L);

        HyePyeResponse hyePyeResponse = getUserByIdCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testGetUserByIdNotPositive: End");

    }

    /**
     * The user should be set on the response for a valid ID.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetUserByIdValid() {

        log.debug("testGetUserByIdValid: Start");

        Long userId = 1L;

        GetUserByIdCommand getUserByIdCommand = (GetUserByIdCommand) applicationContext.getBean(
                "hyepye.service.getUserByIdCommand", userId);

        HyePyeResponse hyePyeResponse = getUserByIdCommand.execute();

        User user = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNotNull(user);
        Assert.assertEquals(userId, user.getId());

        log.debug("testGetUserByIdValid: End");

    }

    /**
     * The user should not be set on the response for a non-existent ID.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetUserByIdNonExistent() {

        log.debug("testGetUserByIdNonExistent: Start");

        Long userId = 100L;

        GetUserByIdCommand getUserByIdCommand = (GetUserByIdCommand) applicationContext.getBean(
                "hyepye.service.getUserByIdCommand", userId);

        HyePyeResponse hyePyeResponse = getUserByIdCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testGetUserByIdNonExistent: End");

    }

}
