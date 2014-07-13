package com.interzonedev.hyepye.service.command.vocabulary;

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
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.TestHelper;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.DuplicateModelException;
import com.interzonedev.hyepye.service.repository.vocabulary.InvalidVocabularyException;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link CreateVocabularyCommand}.
 * 
 * @author mmarkarian
 */
public class CreateVocabularyCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(CreateVocabularyCommandIT.class);

    private static final String TEST_ARMENIAN = "երեկ";
    private static final String TEST_ENGLISH = "yesterday";
    private static final VocabularyType TEST_VOCABULARY_TYPE = VocabularyType.NOUN;
    private static final Status TEST_STATUS = Status.APPROVED;
    private static final Long TEST_USER_ID = 1L;

    @Test
    public void testCreateVocabularyNullVocabulary() {

        log.debug("testCreateVocabularyNullVocabulary: Start");

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", (Vocabulary) null, TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyNullVocabulary: End");

    }

    @Test
    public void testCreateVocabularyInvalidUserId() {

        log.debug("testCreateVocabularyInvalidUserId: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), 0L);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyInvalidUserId: End");

    }

    @Test
    public void testCreateVocabularyNullArmenian() {

        log.debug("testCreateVocabularyNullArmenian: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(null);
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("armenian", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyNullArmenian: End");

    }

    @Test
    public void testCreateVocabularyBlankArmenian() {

        log.debug("testCreateVocabularyBlankArmenian: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian("  ");
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("armenian", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyBlankArmenian: End");

    }

    @Test
    public void testCreateVocabularyArmenianTooLong() {

        log.debug("testCreateVocabularyArmenianTooLong: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(Strings.repeat("a", 256));
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("armenian", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyArmenianTooLong: End");

    }

    @Test
    public void testCreateVocabularyNullEnglish() {

        log.debug("testCreateVocabularyNullEnglish: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(null);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("english", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyNullEnglish: End");

    }

    @Test
    public void testCreateVocabularyBlankEnglish() {

        log.debug("testCreateVocabularyBlankEnglish: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish("  ");
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("english", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyBlankEnglish: End");

    }

    @Test
    public void testCreateVocabularyEnglishTooLong() {

        log.debug("testCreateVocabularyEnglishTooLong: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(Strings.repeat("a", 256));
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("english", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyEnglishTooLong: End");

    }

    @Test
    public void testCreateVocabularyNullVocabularyType() {

        log.debug("testCreateVocabularyNullVocabularyType: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(null);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("vocabularyType", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyNullVocabularyType: End");

    }

    @Test
    public void testCreateVocabularyNullStatus() {

        log.debug("testCreateVocabularyNullStatus: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(null);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals("status", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyNullStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testCreateVocabularyDuplicateArmenian() {

        log.debug("testCreateVocabularyDuplicateArmenian: Start");

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian("բարեւ");
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        DuplicateModelException dme = (DuplicateModelException) hyePyeResponse.getValidationError();

        Assert.assertNotNull(dme);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/vocabulary/before.xml", "vocabulary",
                TestHelper.VOCABULARY_IGNORE_COLUMN_NAMES);

        log.debug("testCreateVocabularyDuplicateArmenian: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testCreateVocabularyValid() {

        log.debug("testCreateVocabularyValid: Start");

        Date now = new Date();

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        Vocabulary vocabularyOut = hyePyeResponse.getVocabulary();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertTrue(vocabularyOut.getId() > 0L);
        Assert.assertEquals(TEST_ARMENIAN, vocabularyOut.getArmenian());
        Assert.assertEquals(TEST_ENGLISH, vocabularyOut.getEnglish());
        Assert.assertEquals(TEST_VOCABULARY_TYPE, vocabularyOut.getVocabularyType());
        Assert.assertEquals(TEST_STATUS, vocabularyOut.getStatus());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(vocabularyOut.getTimeCreated(), now) >= 0);
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(vocabularyOut.getTimeUpdated(), now) >= 0);
        Assert.assertEquals(TEST_USER_ID, vocabularyOut.getCreatedBy());
        Assert.assertEquals(TEST_USER_ID, vocabularyOut.getModifiedBy());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/vocabulary/afterCreate.xml", "vocabulary",
                TestHelper.VOCABULARY_IGNORE_COLUMN_NAMES);

        log.debug("testCreateVocabularyValid: End");

    }

    protected String getSinglePropertyNameFromErrors(Set<ConstraintViolation<Vocabulary>> errors) {
        String errorPropertyName = null;
        ConstraintViolation<Vocabulary> constraintViolation = errors.stream().findFirst().get();

        for (Node node : constraintViolation.getPropertyPath()) {
            errorPropertyName = node.getName();
        }

        return errorPropertyName;
    }

}
