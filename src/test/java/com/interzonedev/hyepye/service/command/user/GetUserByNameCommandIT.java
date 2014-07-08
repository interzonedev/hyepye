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
 * Integration tests for {@link GetUserByNameCommand}.
 * 
 * @author mmarkarian
 */
public class GetUserByNameCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(GetUserByNameCommandIT.class);

    @Inject
    private ApplicationContext applicationContext;

    /**
     * The validation error should be set on the response for a null username.
     */
    @Test
    public void testGetUserByNameNullName() {

        log.debug("testGetUserByNameNullName: Start");

        GetUserByNameCommand getUserByNameCommand = (GetUserByNameCommand) applicationContext.getBean(
                "hyepye.service.getUserByNameCommand", (String) null);

        HyePyeResponse hyePyeResponse = getUserByNameCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testGetUserByNameNullName: End");

    }

    /**
     * The validation error should be set on the response for an empty username.
     */
    @Test
    public void testGetUserByNameEmptyName() {

        log.debug("testGetUserByNameEmptyName: Start");

        GetUserByNameCommand getUserByNameCommand = (GetUserByNameCommand) applicationContext.getBean(
                "hyepye.service.getUserByNameCommand", "");

        HyePyeResponse hyePyeResponse = getUserByNameCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testGetUserByNameEmptyName: End");

    }

    /**
     * The user should be set on the response for a valid username.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetUserByNameValid() {

        log.debug("testGetUserByNameValid: Start");

        String username = "gernb";

        GetUserByNameCommand getUserByNameCommand = (GetUserByNameCommand) applicationContext.getBean(
                "hyepye.service.getUserByNameCommand", username);

        HyePyeResponse hyePyeResponse = getUserByNameCommand.execute();

        User user = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNotNull(user);
        Assert.assertEquals(username, user.getUsername());

        log.debug("testGetUserByNameValid: End");

    }

    /**
     * The user should not be set on the response for a non-existent username.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetUserByNameNonExistent() {

        log.debug("testGetUserByNameNonExistent: Start");

        String username = "this_user_does_not_exist";

        GetUserByNameCommand getUserByNameCommand = (GetUserByNameCommand) applicationContext.getBean(
                "hyepye.service.getUserByNameCommand", username);

        HyePyeResponse hyePyeResponse = getUserByNameCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testGetUserByNameNonExistent: End");

    }

}
