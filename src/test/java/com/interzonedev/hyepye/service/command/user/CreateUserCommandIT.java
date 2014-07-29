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
 * Integration tests for {@link CreateUserCommand}.
 * 
 * @author mmarkarian
 */
public class CreateUserCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(CreateUserCommandIT.class);

    private static final String TEST_USERNAME = "testyt";
    private static final String TEST_PLAINTEXT_PASSWORD = "testpass";
    private static final String TEST_EMAIL = "testy.testerson@test.com";
    private static final String TEST_FIRST_NAME = "Testy";
    private static final String TEST_LAST_NAME = "Testerson";
    private static final Role TEST_ROLE = Role.APPROVER;
    private static final boolean TEST_ACTIVE = true;

    @Test
    public void testCreateUserNullUser() {

        log.debug("testCreateUserNullUser: Start");

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", (User) null, TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserNullUser: End");

    }

    @Test
    public void testCreateUserNullPlaintextPassword() {

        log.debug("testCreateUserNullPlaintextPassword: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), null);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserNullPlaintextPassword: End");

    }

    @Test
    public void testCreateUserNullPlaintextEmpty() {

        log.debug("testCreateUserNullPlaintextEmpty: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), "");

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserNullPlaintextEmpty: End");

    }

    @Test
    public void testCreateUserNullUsername() {

        log.debug("testCreateUserNullUsername: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(null);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
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
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
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
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("username", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserUsernameTooLong: End");

    }

    @Test
    public void testCreateUserNullEmail() {

        log.debug("testCreateUserNullEmail: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(null);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("email", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserNullEmail: End");

    }

    @Test
    public void testCreateUserBlankEmail() {

        log.debug("testCreateUserBlankEmail: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail("  ");
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("email", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserBlankEmail: End");

    }

    @Test
    public void testCreateUserEmailTooLong() {

        log.debug("testCreateUserEmailTooLong: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(Strings.repeat("a", 256));
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("email", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserEmailTooLong: End");

    }

    @Test
    public void testCreateUserFirstNameTooLong() {

        log.debug("testCreateUserFirstNameTooLong: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(Strings.repeat("a", 256));
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("firstName", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserFirstNameTooLong: End");

    }

    @Test
    public void testCreateUserLastNameTooLong() {

        log.debug("testCreateUserLastNameTooLong: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(Strings.repeat("a", 256));
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("lastName", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserLastNameTooLong: End");

    }

    @Test
    public void testCreateUserNullRole() {

        log.debug("testCreateUserNullRole: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(null);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("role", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        log.debug("testCreateUserNullRole: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testCreateUserDuplicateUsername() {

        log.debug("testCreateUserDuplicateUsername: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername("gernb");
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();

        Assert.assertTrue(errors.hasGlobalErrors());
        Assert.assertFalse(errors.hasFieldErrors());
        Assert.assertEquals(1, errors.getGlobalErrorCount());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/before.xml", "hp_user", TestHelper.USER_IGNORE_COLUMN_NAMES);

        log.debug("testCreateUserDuplicateUsername: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testCreateUserDuplicateEmail() {

        log.debug("testCreateUserDuplicateEmail: Start");

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail("gern@blanston.com");
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();

        Assert.assertTrue(errors.hasGlobalErrors());
        Assert.assertFalse(errors.hasFieldErrors());
        Assert.assertEquals(1, errors.getGlobalErrorCount());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getUser());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/before.xml", "hp_user", TestHelper.USER_IGNORE_COLUMN_NAMES);

        log.debug("testCreateUserDuplicateEmail: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testCreateUserValid() {

        log.debug("testCreateUserValid: Start");

        Date now = new Date();

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(TEST_FIRST_NAME);
        userIn.setLastName(TEST_LAST_NAME);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        User userOut = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertTrue(userOut.getId() > 0L);
        Assert.assertEquals(TEST_USERNAME, userOut.getUsername());
        Assert.assertEquals(64, userOut.getPasswordHash().length());
        Assert.assertEquals(10, userOut.getPasswordSeed().length());
        Assert.assertEquals(TEST_EMAIL, userOut.getEmail());
        Assert.assertEquals(TEST_FIRST_NAME, userOut.getFirstName());
        Assert.assertEquals(TEST_LAST_NAME, userOut.getLastName());
        Assert.assertEquals(TEST_ROLE, userOut.getRole());
        Assert.assertEquals(TEST_ACTIVE, userOut.isActive());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeCreated(), now) >= 0);
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterCreate.xml", "hp_user", TestHelper.USER_IGNORE_COLUMN_NAMES);

        log.debug("testCreateUserValid: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/user/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testCreateUserValidNullables() {

        log.debug("testCreateUserValidNullables: Start");

        Date now = new Date();

        User.Builder userIn = User.newBuilder();
        userIn.setUsername(TEST_USERNAME);
        userIn.setEmail(TEST_EMAIL);
        userIn.setFirstName(null);
        userIn.setLastName(null);
        userIn.setRole(TEST_ROLE);
        userIn.setActive(TEST_ACTIVE);

        CreateUserCommand createUserCommand = (CreateUserCommand) applicationContext.getBean(
                "hyepye.service.createUserCommand", userIn.build(), TEST_PLAINTEXT_PASSWORD);

        HyePyeResponse hyePyeResponse = createUserCommand.execute();

        User userOut = hyePyeResponse.getUser();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertTrue(userOut.getId() > 0L);
        Assert.assertEquals(TEST_USERNAME, userOut.getUsername());
        Assert.assertEquals(64, userOut.getPasswordHash().length());
        Assert.assertEquals(10, userOut.getPasswordSeed().length());
        Assert.assertEquals(TEST_EMAIL, userOut.getEmail());
        Assert.assertNull(userOut.getFirstName());
        Assert.assertNull(userOut.getLastName());
        Assert.assertEquals(TEST_ROLE, userOut.getRole());
        Assert.assertEquals(TEST_ACTIVE, userOut.isActive());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeCreated(), now) >= 0);
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(userOut.getTimeUpdated(), now) >= 0);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/user/afterCreateNullables.xml", "hp_user",
                TestHelper.USER_IGNORE_COLUMN_NAMES);

        log.debug("testCreateUserValidNullables: End");

    }

}
