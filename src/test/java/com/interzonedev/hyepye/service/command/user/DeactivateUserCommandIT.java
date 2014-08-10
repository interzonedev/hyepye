package com.interzonedev.hyepye.service.command.user;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyePyeIT;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.TestHelper;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link DeactivateUserCommand}.
 * 
 * @author mmarkarian
 */
public class DeactivateUserCommandIT extends HyePyeIT {

    private static final Logger log = LoggerFactory.getLogger(DeactivateUserCommandIT.class);

    @Test
    public void testDeactivateUserIdNotPositive() {

        log.debug("testDeactivateUserIdNotPositive: Start");

        DeactivateUserCommand deactivateUserCommand = (DeactivateUserCommand) applicationContext.getBean(
                "hyepye.service.deactivateUserCommand", 0L);

        HyePyeResponse hyePyeResponse = deactivateUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testDeactivateUserIdNotPositive: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testDeactivateUserNonExistent() {

        log.debug("testDeactivateUserNonExistent: Start");

        Long userId = 100L;

        DeactivateUserCommand deactivateUserCommand = (DeactivateUserCommand) applicationContext.getBean(
                "hyepye.service.deactivateUserCommand", userId);

        HyePyeResponse hyePyeResponse = deactivateUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/before.xml", "hp_user", null);

        log.debug("testDeactivateUserNonExistent: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testDeactivateUserValid() {

        log.debug("testDeactivateUserValid: Start");

        Date now = new Date();

        Long userId = 1L;

        DeactivateUserCommand deactivateUserCommand = (DeactivateUserCommand) applicationContext.getBean(
                "hyepye.service.deactivateUserCommand", userId);

        HyePyeResponse hyePyeResponse = deactivateUserCommand.execute();

        User user = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNotNull(user);
        Assert.assertEquals(userId, user.getId());
        Assert.assertEquals(false, user.isActive());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(user.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterDeactivate.xml", "hp_user",
                TestHelper.COMMON_IGNORE_COLUMN_NAMES);

        log.debug("testDeactivateUserValid: End");

    }

}
