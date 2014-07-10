package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.vocabulary.InvalidVocabularyException;

/**
 * Integration tests for {@link CreateVocabularyCommand}.
 * 
 * @author mmarkarian
 */
public class CreateVocabularyCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(CreateVocabularyCommandIT.class);

    private static final String TEST_ARMENIAN = "բարեւ";
    private static final String TEST_ENGLISH = "Hello";
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
        vocabularyIn.setEnglish(TEST_ENGLISH);
        vocabularyIn.setVocabularyType(TEST_VOCABULARY_TYPE);
        vocabularyIn.setStatus(TEST_STATUS);

        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), TEST_USER_ID);

        HyePyeResponse hyePyeResponse = createVocabularyCommand.execute();

        InvalidVocabularyException ive = (InvalidVocabularyException) hyePyeResponse.getValidationError();
        Set<ConstraintViolation<Vocabulary>> errors = ive.getErrors();

        // TODO - test error
        ConstraintViolation<Vocabulary> cv = errors.stream().findFirst().get();
        errors.forEach((ConstraintViolation<Vocabulary> cv2) -> {
            log.debug("cv2.getPropertyPath() = " + cv2.getPropertyPath());
        });

        Assert.assertEquals(1, errors.size());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testCreateVocabularyNullArmenian: End");

    }

}
