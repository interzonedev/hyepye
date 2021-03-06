package com.interzonedev.hyepye;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interzonedev.hyepye.model.ModelTestSuite;
import com.interzonedev.hyepye.service.command.CommandTestSuite;
import com.interzonedev.hyepye.service.command.user.UserCommandTestSuite;
import com.interzonedev.hyepye.service.command.vocabulary.VocabularyCommandTestSuite;
import com.interzonedev.hyepye.service.security.SecurityTestSuite;

/**
 * This is a convenience class for running all the tests for the entire Hye Pye application at once by an IDE. This
 * should not be run by the Maven build.
 * 
 * @author mmarkarian
 */
@RunWith(Suite.class)
@SuiteClasses({ ModelTestSuite.class, CommandTestSuite.class, UserCommandTestSuite.class,
        VocabularyCommandTestSuite.class, SecurityTestSuite.class })
public class HyePyeTestSuite {
}
