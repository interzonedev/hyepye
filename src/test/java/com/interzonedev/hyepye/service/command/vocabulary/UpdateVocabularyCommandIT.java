package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import com.google.common.base.Strings;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.TestHelper;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link UpdateVocabularyCommand}.
 * 
 * @author mmarkarian
 */
public class UpdateVocabularyCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(UpdateVocabularyCommandIT.class);

    private static final Long TEST_VOCABULARY_ID = 1L;
    private static final String TEST_ARMENIAN = "երեկ";
    private static final String TEST_ENGLISH = "yesterday";
    private static final VocabularyType TEST_VOCABULARY_TYPE = VocabularyType.DAY;
    private static final Status TEST_STATUS = Status.SUBMITTED;
    private static final Long TEST_USER_ID = 2L;

    @Test
    public void testUpdateVocabularyNullVocabulary() {

        log.debug("testUpdateVocabularyNullVocabulary: Start");

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", (Vocabulary) null, TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyNullVocabulary: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyInvalidUserId() {

        log.debug("testUpdateVocabularyInvalidUserId: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), 0L);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyInvalidUserId: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyNullArmenian() {

        log.debug("testUpdateVocabularyNullArmenian: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setArmenian(null);

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("armenian", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyNullArmenian: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyBlankArmenian() {

        log.debug("testUpdateVocabularyBlankArmenian: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setArmenian("  ");

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("armenian", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyBlankArmenian: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyArmenianTooLong() {

        log.debug("testUpdateVocabularyArmenianTooLong: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setArmenian(Strings.repeat("a", 256));

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("armenian", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyArmenianTooLong: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyNullEnglish() {

        log.debug("testUpdateVocabularyNullEnglish: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setEnglish(null);

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("english", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyNullEnglish: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyBlankEnglish() {

        log.debug("testUpdateVocabularyBlankEnglish: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setEnglish("  ");

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("english", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyBlankEnglish: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyEnglishTooLong() {

        log.debug("testUpdateVocabularyEnglishTooLong: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setEnglish(Strings.repeat("a", 256));

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("english", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyEnglishTooLong: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyNullVocabularyType() {

        log.debug("testUpdateVocabularyNullVocabularyType: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setVocabularyType(null);

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("vocabularyType", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyNullVocabularyType: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyNullStatus() {

        log.debug("testUpdateVocabularyNullStatus: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setStatus(null);

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();
        String errorPropertyName = getSinglePropertyNameFromErrors(errors);

        Assert.assertFalse(errors.hasGlobalErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount());
        Assert.assertEquals("status", errorPropertyName);
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testUpdateVocabularyNullStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyDuplicateArmenian() {

        log.debug("testUpdateVocabularyDuplicateArmenian: Start");

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setArmenian("կապիկ");

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        ValidationException validationException = hyePyeResponse.getValidationError();
        BindingResult errors = validationException.getErrors();

        Assert.assertTrue(errors.hasGlobalErrors());
        Assert.assertFalse(errors.hasFieldErrors());
        Assert.assertEquals(1, errors.getGlobalErrorCount());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/vocabulary/before.xml", "vocabulary",
                TestHelper.VOCABULARY_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateVocabularyDuplicateArmenian: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testUpdateVocabularyValid() {

        log.debug("testUpdateVocabularyValid: Start");

        Date now = new Date();

        Vocabulary testVocabulary = getTestVocabulary(TEST_VOCABULARY_ID);

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder(testVocabulary);
        vocabularyIn.setArmenian(TEST_ARMENIAN);
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = updateVocabularyCommand.execute();

        Vocabulary vocabularyOut = hyePyeResponse.getVocabulary();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());

        Assert.assertEquals(TEST_VOCABULARY_ID, vocabularyOut.getId());
        Assert.assertEquals(TEST_ARMENIAN, vocabularyOut.getArmenian());
        Assert.assertEquals(TEST_ENGLISH, vocabularyOut.getEnglish());
        Assert.assertEquals(TEST_VOCABULARY_TYPE, vocabularyOut.getVocabularyType());
        Assert.assertEquals(TEST_STATUS, vocabularyOut.getStatus());
        Assert.assertEquals(testVocabulary.getTimeCreated(), vocabularyOut.getTimeCreated());
        Assert.assertTrue(dbUnitDataSetTester.compareDatesToTheSecond(vocabularyOut.getTimeUpdated(), now) >= 0);
        Assert.assertEquals(testVocabulary.getCreatedBy(), vocabularyOut.getCreatedBy());
        Assert.assertEquals(TEST_USER_ID, vocabularyOut.getModifiedBy());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(hyepyeDataSource,
                "com/interzonedev/hyepye/dataset/vocabulary/afterUpdate.xml", "vocabulary",
                TestHelper.VOCABULARY_IGNORE_COLUMN_NAMES);

        log.debug("testUpdateVocabularyValid: End");

    }

}
