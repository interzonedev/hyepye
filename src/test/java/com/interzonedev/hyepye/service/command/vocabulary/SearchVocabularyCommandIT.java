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

    private static final String TEST_ENGLISH_FULL_WORD_SINGLE = "Thursday";
    private static final String TEST_ENGLISH_FULL_WORD_MULTIPLE = "green";
    private static final String TEST_ENGLISH_STARTS_WITH = "th";
    private static final String TEST_ENGLISH_CONTAINS = "sd";
    private static final String TEST_ARMENIAN_FULL_WORD = "երկու";
    private static final String TEST_ARMENIAN_STARTS_WITH = "երե";
    private static final String TEST_ARMENIAN_CONTAINS = "եր";

    /* Begin validation error tests. */

    @Test
    public void testSearchVocabularyNonPositiveResultsPerPage() {

        log.debug("testSearchVocabularyNonPositiveResultsPerPage: Start");

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE,
                DefinitionSearchType.FULL_WORD, TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD,
                VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ENGLISH, true, 0, 1);

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
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE,
                DefinitionSearchType.FULL_WORD, TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD,
                VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ENGLISH, true, 5, (Integer) null);

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
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE,
                DefinitionSearchType.FULL_WORD, TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD,
                VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ENGLISH, true, 5, 0);

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
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE, (DefinitionSearchType) null,
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
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE,
                DefinitionSearchType.FULL_WORD, TEST_ARMENIAN_FULL_WORD, (DefinitionSearchType) null,
                VocabularyType.DAY, Status.APPROVED, VocabularyProperty.ENGLISH, true, 5, 1);

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
                "hyepye.service.searchVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE,
                DefinitionSearchType.FULL_WORD, TEST_ARMENIAN_FULL_WORD, DefinitionSearchType.FULL_WORD,
                VocabularyType.DAY, Status.APPROVED, (VocabularyProperty) null, true, 5, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchVocabularyNullOrderBy: End");

    }

    /* Begin valid results tests. */

    /*
     * TODO testSearchEnglishVocabularyEnglishContains, testSearchEnglishVocabularyEnglishStartsWith,
     * testSearchEnglishVocabularyEnglishFullWordSingleResult, testSearchEnglishVocabularyEnglishFullWordMultipleResults
     */

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchVocabulary.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchVocabularyEnglishAndArmenian() {

        log.debug("testSearchVocabularyEnglishAndArmenian: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 17L, 18L });

        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", "green", DefinitionSearchType.FULL_WORD, "կ",
                DefinitionSearchType.STARTS_WITH, VocabularyType.COLOR, Status.APPROVED, VocabularyProperty.ENGLISH,
                true, (Integer) null, 1);

        HyePyeResponse hyePyeResponse = searchVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(expectedIds.size(), vocabularies.size());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyEnglishAndArmenian: End");

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

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchVocabularyAllPossibleResults: End");

    }

}
