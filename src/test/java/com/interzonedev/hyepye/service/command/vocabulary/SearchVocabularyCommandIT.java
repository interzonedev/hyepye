package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyePyeIT;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyProperty;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import com.interzonedev.zankou.dataset.DataSet;

public class SearchVocabularyCommandIT extends HyePyeIT {

    private static final Logger log = LoggerFactory.getLogger(SearchVocabularyCommandIT.class);

    private static final String TEST_ENGLISH_FULL_WORD = "green";
    private static final String TEST_ENGLISH_STARTS_WITH = "th";
    private static final String TEST_ENGLISH_CONTAINS = "day";
    private static final String TEST_ARMENIAN_FULL_WORD = "երկու";
    private static final String TEST_ARMENIAN_STARTS_WITH = "երե";
    private static final String TEST_ARMENIAN_CONTAINS = "եր";

    /* Begin validation error tests. */

    @Test
    public void testSearchVocabularyNonPositiveResultsPerPage() {

        log.debug("testSearchVocabularyNonPositiveResultsPerPage: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, DefinitionSearchType.FULL_WORD,
                TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD, VocabularyType.DAY, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, 0, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchVocabularyNonPositiveResultsPerPage: End");

    }

    @Test
    public void testSearchVocabularyNullRequestedPageNumber() {

        log.debug("testSearchVocabularyNullRequestedPageNumber: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, DefinitionSearchType.FULL_WORD,
                TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD, VocabularyType.DAY, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, 5, (Integer) null);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchVocabularyNullRequestedPageNumber: End");

    }

    @Test
    public void testSearchVocabularyNonPositiveRequestedPageNumber() {

        log.debug("testSearchVocabularyNonPositiveRequestedPageNumber: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, DefinitionSearchType.FULL_WORD,
                TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD, VocabularyType.DAY, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, 5, 0);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchVocabularyNonPositiveRequestedPageNumber: End");

    }

    @Test
    public void testSearchVocabularyNullEnglishSearchType() {

        log.debug("testSearchVocabularyNullEnglishSearchType: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, (DefinitionSearchType) null,
                TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD, VocabularyType.DAY, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, 5, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchVocabularyNullEnglishSearchType: End");

    }

    @Test
    public void testSearchVocabularyNullArmenianSearchType() {

        log.debug("testSearchVocabularyNullArmenianSearchType: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, DefinitionSearchType.FULL_WORD,
                TEST_ARMENIAN_FULL_WORD, (DefinitionSearchType) null, VocabularyType.DAY, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, 5, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchVocabularyNullArmenianSearchType: End");

    }

    @Test
    public void testSearchVocabularyNullOrderBy() {

        log.debug("testSearchVocabularyNullOrderBy: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, DefinitionSearchType.FULL_WORD,
                TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD, VocabularyType.DAY, Status.APPROVED,
                (VocabularyProperty) null, true, 5, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchVocabularyNullOrderBy: End");

    }

    /* Begin valid results tests. */

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyNullEnglish() {

        log.debug("testSearchVocabularyNullEnglish: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 8L, 4L, 9L, 10L, 7L, 5L, 6L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, DefinitionSearchType.CONTAINS, (String) null,
                (DefinitionSearchType) null, VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ENGLISH, true,
                (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyNullEnglish: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyEmptyEnglish() {

        log.debug("testSearchVocabularyEmptyEnglish: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 8L, 4L, 9L, 10L, 7L, 5L, 6L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", "", DefinitionSearchType.CONTAINS, (String) null,
                (DefinitionSearchType) null, VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ENGLISH, true,
                (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyEmptyEnglish: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyEnglishContains() {

        log.debug("testSearchVocabularyEnglishContains: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 8L, 4L, 9L, 10L, 7L, 5L, 6L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_CONTAINS, DefinitionSearchType.CONTAINS,
                (String) null, (DefinitionSearchType) null, VocabularyType.DAY, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyEnglishContains: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyEnglishStartsWith() {

        log.debug("testSearchVocabularyEnglishStartsWith: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 13L, 12L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_STARTS_WITH, DefinitionSearchType.STARTS_WITH,
                (String) null, (DefinitionSearchType) null, VocabularyType.NUMBER, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyEnglishStartsWith: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyEnglishFullWord() {

        log.debug("testSearchVocabularyEnglishFullWord: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 17L, 18L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, DefinitionSearchType.FULL_WORD,
                (String) null, (DefinitionSearchType) null, VocabularyType.COLOR, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyEnglishFullWord: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyNullArmenian() {

        log.debug("testSearchVocabularyNullArmenian: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 12L, 11L, 13L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, VocabularyProperty.ARMENIAN,
                true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyNullArmenian: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyEmptyArmenian() {

        log.debug("testSearchVocabularyEmptyArmenian: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 12L, 11L, 13L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, "",
                DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, VocabularyProperty.ARMENIAN,
                true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyEmptyArmenian: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyArmenianContains() {

        log.debug("testSearchVocabularyArmenianContains: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 12L, 11L, 13L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null,
                TEST_ARMENIAN_CONTAINS, DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED,
                VocabularyProperty.ARMENIAN, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyArmenianContains: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyArmenianStartsWith() {

        log.debug("testSearchVocabularyArmenianStartsWith: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 12L, 13L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null,
                TEST_ARMENIAN_STARTS_WITH, DefinitionSearchType.STARTS_WITH, VocabularyType.NUMBER, Status.APPROVED,
                VocabularyProperty.ARMENIAN, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyArmenianStartsWith: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyArmenianFullWord() {

        log.debug("testSearchVocabularyArmenianFullWord: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 11L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null,
                TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD, VocabularyType.NUMBER, Status.APPROVED,
                VocabularyProperty.ARMENIAN, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyArmenianFullWord: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyEnglishAndArmenian() {

        log.debug("testSearchVocabularyEnglishAndArmenian: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 17L, 18L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD, DefinitionSearchType.FULL_WORD, "կ",
                DefinitionSearchType.STARTS_WITH, VocabularyType.COLOR, Status.APPROVED, VocabularyProperty.ENGLISH,
                true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyEnglishAndArmenian: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyNounVocabularyType() {

        log.debug("testSearchVocabularyNounVocabularyType: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 14L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_CONTAINS, DefinitionSearchType.CONTAINS,
                (String) null, (DefinitionSearchType) null, VocabularyType.NOUN, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyNounVocabularyType: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyNullVocabularyType() {

        log.debug("testSearchVocabularyNullVocabularyType: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 8L, 4L, 9L, 10L, 7L, 5L, 6L, 14L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_CONTAINS, DefinitionSearchType.CONTAINS,
                (String) null, (DefinitionSearchType) null, (VocabularyType) null, Status.APPROVED,
                VocabularyProperty.ENGLISH, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyNullVocabularyType: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyApprovedStatus() {

        log.debug("testSearchVocabularyApprovedStatus: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 1L, 14L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, VocabularyType.NOUN, Status.APPROVED, VocabularyProperty.ENGLISH, true,
                (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyApprovedStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyNullStatus() {

        log.debug("testSearchVocabularyNullStatus: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 16L, 1L, 2L, 3L, 14L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, VocabularyType.NOUN, (Status) null, VocabularyProperty.ENGLISH, true,
                (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyNullStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyOrderByStatus() {

        log.debug("testSearchVocabularyOrderByStatus: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 1L, 14L, 3L, 2L, 16L, });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, VocabularyType.NOUN, (Status) null, VocabularyProperty.STATUS, true,
                (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyOrderByStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyAscending() {

        log.debug("testSearchVocabularyAscending: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 4L, 5L, 6L, 7L, 8L, 9L, 10L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_CONTAINS, DefinitionSearchType.CONTAINS,
                (String) null, (DefinitionSearchType) null, VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ID,
                true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyAscending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyDescending() {

        log.debug("testSearchVocabularyDescending: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 10L, 9L, 8L, 7L, 6L, 5L, 4L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_CONTAINS, DefinitionSearchType.CONTAINS,
                (String) null, (DefinitionSearchType) null, VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ID,
                false, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyDescending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyNullResultsPerPage() {

        log.debug("testSearchVocabularyNullResultsPerPage: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L,
                14L, 15L, 16L, 17L, 18L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, (VocabularyType) null, (Status) null, VocabularyProperty.ID, true,
                (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyNullResultsPerPage: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyResultsPerPageLessThanNumberOfResults() {

        log.debug("testSearchVocabularyResultsPerPageLessThanNumberOfResults: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 1L, 2L, 3L, 4L, 5L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, (VocabularyType) null, (Status) null, VocabularyProperty.ID, true,
                expectedIds.size(), 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(4, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyResultsPerPageLessThanNumberOfResults: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyResultsPerPageGreaterThanNumberOfResults() {

        log.debug("testSearchVocabularyResultsPerPageGreaterThanNumberOfResults: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L,
                14L, 15L, 16L, 17L, 18L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, (VocabularyType) null, (Status) null, VocabularyProperty.ID, true, 20, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyResultsPerPageGreaterThanNumberOfResults: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyRequestedPageNumberLessThanMaxPages() {

        log.debug("testSearchVocabularyRequestedPageNumberLessThanMaxPages: Start");

        Integer resultsPerPage = 5;
        Integer requestedPageNumber = 3;

        List<Long> expectedIds = Arrays.asList(new Long[] { 11L, 12L, 13L, 14L, 15L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, (VocabularyType) null, (Status) null, VocabularyProperty.ID, true,
                resultsPerPage, requestedPageNumber);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(4, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(3, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyRequestedPageNumberLessThanMaxPages: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyRequestedPageNumberGreaterThanMaxPages() {

        log.debug("testSearchVocabularyRequestedPageNumberGreaterThanMaxPages: Start");

        Integer resultsPerPage = 5;
        Integer requestedPageNumber = 7;

        List<Long> expectedIds = Arrays.asList(new Long[] { 14L, 15L, 16L, 17L, 18L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, (VocabularyType) null, (Status) null, VocabularyProperty.ID, true,
                resultsPerPage, requestedPageNumber);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(4, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(4, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyRequestedPageNumberGreaterThanMaxPages: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyAllPossibleResults() {

        log.debug("testSearchVocabularyAllPossibleResults: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 16L, 8L, 4L, 9L, 10L, 7L, 5L, 6L, 18L, 17L, 1L, 2L, 3L,
                15L, 13L, 12L, 11L, 14L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", (String) null, (DefinitionSearchType) null, (String) null,
                (DefinitionSearchType) null, (VocabularyType) null, (Status) null, VocabularyProperty.ENGLISH, true,
                (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());
        Assert.assertEquals(1, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(1, hyePyeResponse.getReturnedPageNumber().intValue());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyAllPossibleResults: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyNoResults() {

        log.debug("testSearchVocabularyAllPossibleResults: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", "this_does_not_exist", DefinitionSearchType.FULL_WORD,
                (String) null, (DefinitionSearchType) null, (VocabularyType) null, (Status) null,
                VocabularyProperty.ENGLISH, true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertTrue(vocabularies.isEmpty());
        Assert.assertEquals(0, hyePyeResponse.getNumberOfPages().intValue());
        Assert.assertEquals(0, hyePyeResponse.getReturnedPageNumber().intValue());

        log.debug("testSearchVocabularyAllPossibleResults: End");

    }

}
