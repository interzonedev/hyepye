package com.interzonedev.hyepye.service.command.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is a convenience class for running all the tests for the {@link com.interzonedev.hyepye.service.command.user}
 * package at once by an IDE. This should not be run by the Maven build.
 * 
 * @author mmarkarian
 */
@RunWith(Suite.class)
@SuiteClasses({ CreateUserCommandIT.class, DeactivateUserCommandIT.class, GetAllUsersCommandIT.class,
        GetUserByIdCommandIT.class, GetUserByNameCommandIT.class, PasswordHelperIT.class, UpdateUserCommandIT.class })
public class UserCommandTestSuite {
}
