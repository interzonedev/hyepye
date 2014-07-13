package com.interzonedev.hyepye.service.command.user;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.Role;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.TestHelper;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.user.InvalidUserException;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link CreateUserCommand}.
 * 
 * @author mmarkarian
 */
public class CreateUserCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(CreateUserCommandIT.class);

    private static final String TEST_USERNAME = "testyt";
    private static final String TEST_PASSWORD_HASH = "4bc75035d73f6083683e040fc31f28e0ec6d1cbce5cb0a5e2611eb89bceb6c16";
    private static final String TEST_PASSWORD_SEED = "0123456789";
    private static final String TEST_EMAIL = "testy.testerson@test.com";
    private static final String TEST_FIRST_NAME = "Testy";
    private static final String TEST_LAST_NAME = "Testerson";
    private static final Role TEST_ROLE = Role.APPROVER;
    private static final boolean TEST_ACTIVE = true;

    @Test
    public void testCreateUserNullUser() {

        log.debug("testCreateUserNullUser: Start");

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", (User) null);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserNullUser: End");

    }

    @Test
    public void testCreateUserNullUsername() {

        log.debug("testCreateUserNullUsername: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(null);
        userIn.setPasswordHash(TEST_PASSWORD_HASH);
        userIn.setPasswordSeed(TEST_PASSWORD_SEED);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        InvalidUserException ive = (InvalidUserException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<User>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("username", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserNullUsername: End");

    }

    @Test
    public void testCreateUserBlankUsername() {

        log.debug("testCreateUserBlankUsername: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername("  ");
        userIn.setPasswordHash(TEST_PASSWORD_HASH);
        userIn.setPasswordSeed(TEST_PASSWORD_SEED);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        InvalidUserException ive = (InvalidUserException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<User>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("username", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserBlankUsername: End");

    }

    @Test
    public void testCreateUserUsernameTooLong() {

        log.debug("testCreateUserUsernameTooLong: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(Strings.repeat("a", 256));
        userIn.setPasswordHash(TEST_PASSWORD_HASH);
        userIn.setPasswordSeed(TEST_PASSWORD_SEED);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        InvalidUserException ive = (InvalidUserException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<User>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("username", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserUsernameTooLong: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testCreateUserValid() {

        log.debug("testCreateUserValid: Start");

        Date now = new Date();

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setPasswordHash(TEST_PASSWORD_HASH);
        userIn.setPasswordSeed(TEST_PASSWORD_SEED);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        User userOut = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertTrue(userOut.getId() > 0L);
        Assert.assertEquals(TEST_USERNAME, userOut.getUsername());
        Assert.assertEquals(TEST_PASSWORD_HASH, userOut.getPasswordHash());
        Assert.assertEquals(TEST_PASSWORD_SEED, userOut.getPasswordSeed());
        Assert.assertEquals(TEST_EMAIL, userOut.getEmail());
        Assert.assertEquals(TEST_FIRST_NAME, userOut.getFirstName());
        Assert.assertEquals(TEST_LAST_NAME, userOut.getLastName());
        Assert.assertEquals(TEST_ROLE, userOut.getRole());
        Assert.assertEquals(TEST_ACTIVE, userOut.isActive());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeCreated(), now) >= 0);
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterCreate.xml", "hp_user",
                TestHelper.USER_IGNORE_COLUMN_NAMES);
        
        log.debug("testCreateUserValid: End");

    }

    protected String getSinglePropertyNameFromErrors(Set<ConstraintViolation<User>> errors) {
        String errorPropertyName = null;
        ConstraintViolation<User> constraintViolation = errors.stream().findFirst().get();

        for (Node node : constraintViolation.getPropertyPath()) {
            errorPropertyName = node.getName();
        }

        return errorPropertyName;
    }

}
