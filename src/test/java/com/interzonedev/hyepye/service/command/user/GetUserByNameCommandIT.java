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

}
