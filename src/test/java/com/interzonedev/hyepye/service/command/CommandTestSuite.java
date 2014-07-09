package com.interzonedev.hyepye.service.command;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interzonedev.hyepye.service.command.user.GetUserByNameCommandIT;

/**
 * This is a convenience class for running all the tests for the {@link com.interzonedev.hyepye.service.command} package
 * at once by an IDE. This should not be run by the Maven build.
 * 
 * @author mmarkarian
 */
@RunWith(Suite.class)
@SuiteClasses({ GetUserByNameCommandIT.class, HyePyeResponseTest.class })
public class CommandTestSuite {
}
