package com.interzonedev.hyepye.service.command.user;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyePyeIT;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link GetAllUsersCommand}.
 * 
 * @author mmarkarian
 */
public class GetAllUsersCommandIT extends HyePyeIT {

    private static final Logger log = LoggerFactory.getLogger(GetAllUsersCommandIT.class);

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/empty.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllUsersEmpty() {

        log.debug("testGetAllUsersEmpty: Start");

        GetAllUsersCommand getAllUsersCommand = (GetAllUsersCommand) applicationContext
                .getBean("hyepye.service.getAllUsersCommand");

        HyePyeResponse hyePyeResponse = getAllUsersCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getUsers().size());

        log.debug("testGetAllUsersEmpty: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllUsersNonEmpty() {

        log.debug("testGetAllUsersNonEmpty: Start");

        GetAllUsersCommand getAllUsersCommand = (GetAllUsersCommand) applicationContext
                .getBean("hyepye.service.getAllUsersCommand");

        HyePyeResponse hyePyeResponse = getAllUsersCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getUsers().size());

        log.debug("testGetAllUsersNonEmpty: End");

    }

}
