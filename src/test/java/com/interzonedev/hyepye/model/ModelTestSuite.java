package com.interzonedev.hyepye.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is a convenience class for running all the tests for the {@link com.interzonedev.hyepye.model} package at once
 * by an IDE. This should not be run by the Maven build.
 * 
 * @author mmarkarian
 */
@RunWith(Suite.class)
@SuiteClasses({ ConjugationTest.class, UserTest.class, VerbTest.class })
public class ModelTestSuite {
}
