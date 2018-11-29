package com.interzonedev.hyepye;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BindingResult;

import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.TestHelper;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.command.user.GetUserByIdCommand;
import com.interzonedev.hyepye.service.command.vocabulary.GetVocabularyByIdCommand;
import com.interzonedev.zankou.AbstractIntegrationTest;
import com.interzonedev.zankou.dataset.dbunit.DbUnitDataSetTester;

/**
 * Abstract class for all Hye Pye application integration tests. Initiates the test Spring container and makes common
 * beans for testing available.
 * 
 * @author mmarkarian
 */
@ContextConfiguration(locations = { "classpath:com/interzonedev/hyepye/spring/applicationContext-test.xml" })
public abstract class HyePyeIT extends AbstractIntegrationTest {

    /**
     * Used in implementing tests for command bean creation.
     */
    @Inject
    protected ApplicationContext applicationContext;

    /**
     * Used in implementing integration tests to insert, read and delete test data in the live schema.
     */
    @Inject
    @Named("hyepye.service.dataSource")
    protected DataSource hyepyeDataSource;

    /**
     * Used in implementing integration tests to compare data in the live schema.
     */
    @Inject
    @Named("hyepye.common.dbUnitDataSetTester")
    protected DbUnitDataSetTester dbUnitDataSetTester;

    /**
     * Used in implementing integration tests to compare data in the live schema.
     */
    @Inject
    @Named("hyepye.service.testHelper")
    protected TestHelper testHelper;

    @Inject
    @Named("hyepye.service.passwordEncoder")
    protected PasswordEncoder passwordEncoder;

    /**
     * Extract the name of the {property that is in error from the specified {@link BindingResult}. Looks at the first
     * error only.
     * 
     * @param errors The {@link BindingResult} that contains the error that occured from validating a model.
     * 
     * @return Returns the name of the property that is in error from the specified {@link BindingResult}.
     */
    protected String getSinglePropertyNameFromErrors(BindingResult errors) {

        return errors.getFieldError().getField();

    }

    /**
     * Gets the {@link User} with the specified ID.
     * 
     * @param userId The ID of the {@link User} to retrieve.
     * 
     * @return Returns the {@link User} with the specified ID if it exists, otherwise returns null.
     */
    protected User getTestUser(Long userId) {

        GetUserByIdCommand getUserByIdCommand = (GetUserByIdCommand) applicationContext.getBean(
                "hyepye.service.getUserByIdCommand", userId);

        HyePyeResponse hyePyeResponse = getUserByIdCommand.execute();

        return hyePyeResponse.getUser();

    }

    /**
     * Gets the {@link Vocabulary} with the specified ID.
     * 
     * @param vocabularyId The ID of the {@link Vocabulary} to retrieve.
     * 
     * @return Returns the {@link Vocabulary} with the specified ID if it exists, otherwise returns null.
     */
    protected Vocabulary getTestVocabulary(Long vocabularyId) {

        GetVocabularyByIdCommand getVocabularyByIdCommand = (GetVocabularyByIdCommand) applicationContext.getBean(
                "hyepye.service.getVocabularyByIdCommand", vocabularyId);

        HyePyeResponse hyePyeResponse = getVocabularyByIdCommand.execute();

        return hyePyeResponse.getVocabulary();

    }

}
