package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyProperty;
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
     * A null vocabulary property should result in a validation error.
     */
    @Test
    public void testGetAllVocabulariesNullOrderBy() {

        log.debug("testGetAllVocabulariesNullOrderBy: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", (VocabularyProperty) null);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesNullOrderBy: End");

    }

    /**
     * A negative limit should result in a validation error.
     */
    @Test
    public void testGetAllVocabulariesNegativeLimit() {

        log.debug("testGetAllVocabulariesNegativeLimit: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, -1L, 0L);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesNegativeLimit: End");

    }

    /**
     * A negative offset should result in a validation error.
     */
    @Test
    public void testGetAllVocabulariesNegativeOffset() {

        log.debug("testGetAllVocabulariesNegativeOffset: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, null, -1L);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesNegativeOffset: End");

    }

    /**
     * A zero limit should return no results.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesZeroLimit() {

        log.debug("testGetAllVocabulariesZeroLimit: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, 0L, 0L);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesZeroLimit: End");

    }

    /**
     * A non-zero limit should return a limited set of results.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesNonZeroLimit() {

        log.debug("testGetAllVocabulariesNonZeroLimit: Start");

        Long limit = 5L;

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, limit, 0L);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesNonZeroLimit: End");

    }

    /**
     * A null limit should return all results.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesNullLimit() {

        log.debug("testGetAllVocabulariesNullLimit: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, null, 0L);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(10, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesNullLimit: End");

    }

    /**
     * A zero offset should return all results.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesZeroOffset() {

        log.debug("testGetAllVocabulariesZeroOffset: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, null, 0L);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(10, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesZeroOffset: End");

    }

    /**
     * A non-zero offset should return a limited set of results.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesZeroNonOffset() {

        log.debug("testGetAllVocabulariesZeroNonOffset: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, null, 6L);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(4, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesZeroNonOffset: End");

    }

    /**
     * A null offset should return all results.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesNullOffset() {

        log.debug("testGetAllVocabulariesNullOffset: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN, true, null, null);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(10, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesNullOffset: End");

    }

    /**
     * An empty vocabulary table should return an empty vocabularies collection on the response.
     */
    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/empty.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesEmpty() {

        log.debug("testGetAllVocabulariesEmpty: Start");

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ARMENIAN);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testGetAllVocabulariesEmpty: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesOrderByIdAscending() {

        log.debug("testGetAllVocabulariesOrderByIdAscending: Start");

        Long limit = 5L;
        Long offset = 2L;

        List<Long> expectedIds = Arrays.asList(new Long[] { 3L, 4L, 5L, 6L, 7L });

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ID, true, limit, offset);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testGetAllVocabulariesOrderByIdAscending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesOrderByIdDescending() {

        log.debug("testGetAllVocabulariesOrderByIdDescending: Start");

        Long limit = 5L;
        Long offset = 2L;

        List<Long> expectedIds = Arrays.asList(new Long[] { 8L, 7L, 6L, 5L, 4L });

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ID, false, limit, offset);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testGetAllVocabulariesOrderByIdDescending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/before.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testGetAllVocabulariesOrderByEnglishAscending() {

        log.debug("testGetAllVocabulariesOrderByEnglishAscending: Start");

        Long limit = 5L;
        Long offset = 2L;

        List<Long> expectedIds = Arrays.asList(new Long[] { 9L, 10L, 7L, 5L, 6L });

        GetAllVocabulariesCommand getAllVocabulariesCommand = (GetAllVocabulariesCommand) applicationContext.getBean(
                "hyepye.service.getAllVocabulariesCommand", VocabularyProperty.ENGLISH, true, limit, offset);

        HyePyeResponse hyePyeResponse = getAllVocabulariesCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testGetAllVocabulariesOrderByEnglishAscending: End");

    }

}
