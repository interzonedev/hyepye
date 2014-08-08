package com.interzonedev.hyepye.service.command.user;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import com.google.common.base.Strings;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.Role;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.TestHelper;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * 
 * Integration tests for {@link UpdateUserCommand}.
 * 
 * @author mmarkarian
 */
public class UpdateUserCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserCommandIT.class);

    private static final Long TEST_USER_ID = 1L;
    private static final String TEST_USERNAME = "testyt";
    private static final String TEST_CURRENT_PLAINTEXT_PASSWORD = "testpass";
    private static final String TEST_NEW_PLAINTEXT_PASSWORD = "newtestpass";
    private static final String TEST_EMAIL = "testy.testerson@test.com";
    private static final String TEST_FIRST_NAME = "Testy";
    private static final String TEST_LAST_NAME = "Testerson";
    private static final Role TEST_ROLE = Role.APPROVER;

    @Test
    public void testUpdateUserUpdatingPasswordNullUser() {

        log.debug("testUpdateUserUpdatingPasswordNullUser: Start");

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", (User) null, TEST_CURRENT_PLAINTEXT_PASSWORD,
                TEST_NEW_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserUpdatingPasswordNullUser: End");

    }

    @Test
    public void testUpdateUserNotUpdatingPasswordNullUser() {

        log.debug("testUpdateUserNotUpdatingPasswordNullUser: Start");

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", (User) null);

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserNotUpdatingPasswordNullUser: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserCurrentPasswordMismatch() {

        log.debug("testUpdateUserCurrentPasswordMismatch: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setId(TEST_USER_ID);
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build(), "wrongpassword", TEST_NEW_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserCurrentPasswordMismatch: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserNullUsername() {

        log.debug("testUpdateUserNullUsername: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername(null);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("username", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserNullUsername: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserBlankUsername() {

        log.debug("testUpdateUserBlankUsername: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername("  ");

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("username", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserBlankUsername: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserUsernameTooLong() {

        log.debug("testUpdateUserUsernameTooLong: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername(Strings.repeat("a", 256));

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("username", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserUsernameTooLong: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserNullEmail() {

        log.debug("testUpdateUserNullEmail: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setEmail(null);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("email", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserNullEmail: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserBlankEmail() {

        log.debug("testUpdateUserBlankEmail: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setEmail("  ");

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("email", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserBlankEmail: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserEmailTooLong() {

        log.debug("testUpdateUserEmailTooLong: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setEmail(Strings.repeat("a", 256));

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("email", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserEmailTooLong: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserFirstNameTooLong() {

        log.debug("testUpdateUserFirstNameTooLong: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setFirstName(Strings.repeat("a", 256));

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("firstName", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserFirstNameTooLong: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserLastNameTooLong() {

        log.debug("testUpdateUserLastNameTooLong: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setLastName(Strings.repeat("a", 256));

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("lastName", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserLastNameTooLong: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserNullRole() {

        log.debug("testUpdateUserNullRole: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setRole(null);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("role", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testUpdateUserNullRole: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserDuplicateUsername() {

        log.debug("testUpdateUserDuplicateUsername: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername("gernb");

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();

        Assert.assertTrue(errors.hasGlobalErrors());
        Assert.assertFalse(errors.hasFieldErrors());
        Assert.assertEquals(1, errors.getGlobalErrorCount());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/before.xml", "hp_user", TestHelper.USER_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateUserDuplicateUsername: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserDuplicateEmail() {

        log.debug("testUpdateUserDuplicateEmail: Start");

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setEmail("gern@blanston.com");

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();

        Assert.assertTrue(errors.hasGlobalErrors());
        Assert.assertFalse(errors.hasFieldErrors());
        Assert.assertEquals(1, errors.getGlobalErrorCount());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/before.xml", "hp_user", TestHelper.USER_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateUserDuplicateEmail: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserValidUpdatingPassword() {

        log.debug("testUpdateUserValidUpdatingPassword: Start");

        Date now = new Date();

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build(), TEST_CURRENT_PLAINTEXT_PASSWORD,
                TEST_NEW_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        User userOut = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(TEST_USER_ID, userOut.getId());
        Assert.assertEquals(TEST_USERNAME, userOut.getUsername());
        Assert.assertTrue(passwordEncoder.matches(TEST_NEW_PLAINTEXT_PASSWORD, userOut.getPasswordHash()));
        Assert.assertEquals(TEST_EMAIL, userOut.getEmail());
        Assert.assertEquals(TEST_FIRST_NAME, userOut.getFirstName());
        Assert.assertEquals(TEST_LAST_NAME, userOut.getLastName());
        Assert.assertEquals(TEST_ROLE, userOut.getRole());
        Assert.assertTrue(userOut.isActive());
        Assert.assertEquals(testUser.getTimeCreated(), userOut.getTimeCreated());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterUpdateWithPassword.xml", "hp_user",
                TestHelper.USER_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateUserValidUpdatingPassword: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserValidNotUpdatingPassword() {

        log.debug("testUpdateUserValidNotUpdatingPassword: Start");

        Date now = new Date();

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        User userOut = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(TEST_USER_ID, userOut.getId());
        Assert.assertEquals(TEST_USERNAME, userOut.getUsername());
        Assert.assertEquals(testUser.getPasswordHash(), userOut.getPasswordHash());
        Assert.assertEquals(TEST_EMAIL, userOut.getEmail());
        Assert.assertEquals(TEST_FIRST_NAME, userOut.getFirstName());
        Assert.assertEquals(TEST_LAST_NAME, userOut.getLastName());
        Assert.assertEquals(TEST_ROLE, userOut.getRole());
        Assert.assertTrue(userOut.isActive());
        Assert.assertEquals(testUser.getTimeCreated(), userOut.getTimeCreated());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterUpdateWithoutPassword.xml", "hp_user",
                TestHelper.COMMON_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateUserValidNotUpdatingPassword: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserValidAttemptToUpdateActive() {

        log.debug("testUpdateUserValidAttemptToUpdateActive: Start");

        Date now = new Date();

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(false);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        User userOut = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(TEST_USER_ID, userOut.getId());
        Assert.assertEquals(TEST_USERNAME, userOut.getUsername());
        Assert.assertEquals(testUser.getPasswordHash(), userOut.getPasswordHash());
        Assert.assertEquals(TEST_EMAIL, userOut.getEmail());
        Assert.assertEquals(TEST_FIRST_NAME, userOut.getFirstName());
        Assert.assertEquals(TEST_LAST_NAME, userOut.getLastName());
        Assert.assertEquals(TEST_ROLE, userOut.getRole());
        Assert.assertTrue(userOut.isActive());
        Assert.assertEquals(testUser.getTimeCreated(), userOut.getTimeCreated());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterUpdateWithoutPassword.xml", "hp_user",
                TestHelper.COMMON_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateUserValidAttemptToUpdateActive: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateUserValidNullables() {

        log.debug("testUpdateUserValidNullables: Start");

        Date now = new Date();

        User testUser = getTestUser(TEST_USER_ID);

        User.Builder userIn = User.newBuilder(testUser);
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(null);
        userIn.setLastName(null);
        userIn.setRole(TEST_ROLE);

        UpdateUserCommand updateUserCommand = (UpdateUserCommand) applicationContext.getBean(
                "hyepye.service.updateUserCommand", userIn.build());

        HyePyeResponse hyePyeResponse = updateUserCommand.execute();

        User userOut = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(TEST_USER_ID, userOut.getId());
        Assert.assertEquals(TEST_USERNAME, userOut.getUsername());
        Assert.assertEquals(testUser.getPasswordHash(), userOut.getPasswordHash());
        Assert.assertEquals(TEST_EMAIL, userOut.getEmail());
        Assert.assertNull(userOut.getFirstName());
        Assert.assertNull(userOut.getLastName());
        Assert.assertEquals(TEST_ROLE, userOut.getRole());
        Assert.assertTrue(userOut.isActive());
        Assert.assertEquals(testUser.getTimeCreated(), userOut.getTimeCreated());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterUpdateNullables.xml", "hp_user",
                TestHelper.EXPANDED_USER_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateUserValidNullables: End");

    }

    private User getTestUser(Long userId) {

        GetUserByIdCommand getUserByIdCommand = (GetUserByIdCommand) applicationContext.getBean(
                "hyepye.service.getUserByIdCommand", userId);

        HyePyeResponse hyePyeResponse = getUserByIdCommand.execute();

        User testUser = hyePyeResponse.getUser();

        return testUser;

    }

}
