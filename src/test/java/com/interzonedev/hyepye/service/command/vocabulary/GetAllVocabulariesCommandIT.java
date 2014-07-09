package com.interzonedev.hyepye.service.command.vocabulary;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link GetAllVocabulariesCommand}.
 * 
 * @author mmarkarian
 */
public class GetAllVocabulariesCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(GetAllVocabulariesCommandIT.class);

    /**
     * An empty vocabulary table should return an empty vocabularies collection on the response.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/empty.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesEmpty() {

        log.debug("testGetAllVocabulariesEmpty: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext
                .getBean("hyepye.service.getAllVocabulariesCommand");

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesEmpty: End");

    }

    /**
     * A non-empty vocabulary table should return a non-empty vocabularies collection on the response.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesNonEmpty() {

        log.debug("testGetAllVocabulariesNonEmpty: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext
                .getBean("hyepye.service.getAllVocabulariesCommand");

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesNonEmpty: End");

    }

}
