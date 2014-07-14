package com.interzonedev.hyepye.service.command.vocabulary;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link GetVocabularyByIdCommand}.
 * 
 * @author mmarkarian
 */
public class GetVocabularyByIdCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(GetVocabularyByIdCommandIT.class);

    /**
     * The validation error message should be set on the response for a non positive ID.
     */
    @Test
    public void testGetVocabularyByIdNotPositive() {

        log.debug("testGetVocabularyByIdNotPositive: Start");

        GetVocabularyByIdCommand getVocabularyByIdCommand = (GetVocabularyByIdCommand) applicationContext.getBean(
                "hyepye.service.getVocabularyByIdCommand", 0L);

        HyePyeResponse hyePyeResponse = getVocabularyByIdCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testGetVocabularyByIdNotPositive: End");

    }

    /**
     * The vocabulary should be set on the response for a valid ID.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetVocabularyByIdValid() {

        log.debug("testGetVocabularyByIdValid: Start");

        Long vocabularyId = 1L;

        GetVocabularyByIdCommand getVocabularyByIdCommand = (GetVocabularyByIdCommand) applicationContext.getBean(
                "hyepye.service.getVocabularyByIdCommand", vocabularyId);

        HyePyeResponse hyePyeResponse = getVocabularyByIdCommand.execute();

        Vocabulary vocabulary = hyePyeResponse.getVocabulary();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNotNull(vocabulary);
        Assert.assertEquals(vocabularyId, vocabulary.getId());

        log.debug("testGetVocabularyByIdValid: End");

    }

    /**
     * The vocabulary should not be set on the response for a non-existent ID.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetVocabularyByIdNonExistent() {

        log.debug("testGetVocabularyByIdNonExistent: Start");

        Long vocabularyId = 100L;

        GetVocabularyByIdCommand getVocabularyByIdCommand = (GetVocabularyByIdCommand) applicationContext.getBean(
                "hyepye.service.getVocabularyByIdCommand", vocabularyId);

        HyePyeResponse hyePyeResponse = getVocabularyByIdCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testGetVocabularyByIdNonExistent: End");

    }

}
