package com.interzonedev.hyepye;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.TestHelper;
import com.interzonedev.zankou.AbstractIntegrationTest;
import com.interzonedev.zankou.dataset.dbunit.DbUnitDataSetTester;

/**
 * Abstract class for all Hye Pye application integration tests. Initiates the test Spring container and makes common
 * beans for testing available.
 * 
 * @author mmarkarian
 */
@ContextConfiguration(locations = { "classpath:com/interzonedev/hyepye/spring/applicationContext-test.xml" })
public abstract class HyepyeAbstractIT extends AbstractIntegrationTest {

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

    /**
     * Extract the name of the {@link User} property that is in error from the specified {@link Set
     * <ConstraintViolation<User>>}. Looks at the first error only.
     * 
     * @param errors The {@link Set<ConstraintViolation<User>>} that contains the error that occured from validating a
     *            {@link User}.
     * 
     * @return Returns the name of the {@link User} property that is in error from the specified {@link Set
     *         <ConstraintViolation<User>>}.
     */
    protected String getSinglePropertyNameFromUserErrors(Set<ConstraintViolation<User>> errors) {
        String errorPropertyName = null;
        ConstraintViolation<User> constraintViolation = errors.stream().findFirst().get();

        for (Node node : constraintViolation.getPropertyPath()) {
            errorPropertyName = node.getName();
        }

        return errorPropertyName;
    }

    /**
     * Extract the name of the {@link Vocabulary} property that is in error from the specified {@link Set
     * <ConstraintViolation<Vocabulary>>}. Looks at the first error only.
     * 
     * @param errors The {@link Set<ConstraintViolation<Vocabulary>>} that contains the error that occured from
     *            validating a {@link Vocabulary}.
     * 
     * @return Returns the name of the {@link Vocabulary} property that is in error from the specified {@link Set
     *         <ConstraintViolation<Vocabulary>>}.
     */
    protected String getSinglePropertyNameFromVocabularyErrors(Set<ConstraintViolation<Vocabulary>> errors) {
        String errorPropertyName = null;
        ConstraintViolation<Vocabulary> constraintViolation = errors.stream().findFirst().get();

        for (Node node : constraintViolation.getPropertyPath()) {
            errorPropertyName = node.getName();
        }

        return errorPropertyName;
    }

}
