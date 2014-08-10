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
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link SearchEnglishVocabularyCommand}.
 * 
 * @author mmarkarian
 */
public class SearchEnglishVocabularyCommandIT extends HyePyeIT {

    private static final Logger log = LoggerFactory.getLogger(SearchEnglishVocabularyCommandIT.class);

    private static final String TEST_ENGLISH_FULL_WORD_SINGLE = "Thursday";
    private static final String TEST_ENGLISH_FULL_WORD_MULTIPLE = "green";
    private static final String TEST_ENGLISH_STARTS_WITH = "th";
    private static final String TEST_ENGLISH_CONTAINS = "sd";

    @Test
    public void testSearchEnglishVocabularyNullEnglish() {

        log.debug("testSearchEnglishVocabularyNullEnglish: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", null, DefinitionSearchType.CONTAINS,
                        VocabularyType.NOUN, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNullEnglish: End");

    }

    @Test
    public void testSearchEnglishVocabularyNullDefinitionSearchType() {

        log.debug("testSearchEnglishVocabularyNullDefinitionSearchType: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE, null,
                        VocabularyType.NOUN, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNullDefinitionSearchType: End");

    }

    @Test
    public void testSearchEnglishVocabularyNegativeLimit() {

        log.debug("testSearchEnglishVocabularyNegativeLimit: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, Status.APPROVED, true, -1L, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNegativeLimit: End");

    }

    @Test
    public void testSearchEnglishVocabularyNegativeOffset() {

        log.debug("testSearchEnglishVocabularyNegativeOffset: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, Status.APPROVED, true, null, -1L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNegativeOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyNullVocabularyType() {

        log.debug("testSearchEnglishVocabularyNullVocabularyType: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, null, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, vocabularies.size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNullVocabularyType: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyNounVocabularyType() {

        log.debug("testSearchEnglishVocabularyNounVocabularyType: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, vocabularies.size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNounVocabularyType: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyNullStatus() {

        log.debug("testSearchEnglishVocabularyNullVocabularyType: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, null, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, vocabularies.size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNullStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyApprovedStatus() {

        log.debug("testSearchEnglishVocabularyApprovedStatus: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, vocabularies.size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyApprovedStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyAscending() {

        log.debug("testSearchEnglishVocabularyAscending: Start");

        Long limit = 2L;
        Long offset = 1L;

        List<Long> expectedIds = Arrays.asList(new Long[] { 5L, 6L });

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, limit, offset);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchEnglishVocabularyAscending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyDescending() {

        log.debug("testSearchEnglishVocabularyDescending: Start");

        Long limit = 2L;
        Long offset = 1L;

        List<Long> expectedIds = Arrays.asList(new Long[] { 5L, 7L });

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, false, limit, offset);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchEnglishVocabularyDescending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyZeroLimit() {

        log.debug("testSearchEnglishVocabularyZeroLimit: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, 0L, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyZeroLimit: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyNonZeroLimit() {

        log.debug("testSearchEnglishVocabularyNonZeroLimit: Start");

        Long limit = 2L;

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, limit, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNonZeroLimit: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyNullLimit() {

        log.debug("testSearchEnglishVocabularyNullLimit: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNullLimit: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyZeroOffset() {

        log.debug("testSearchEnglishVocabularyZeroOffset: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyZeroOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyNonZeroOffset() {

        log.debug("testSearchEnglishVocabularyNonZeroOffset: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, 1L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(2, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNonZeroOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyNullOffset() {

        log.debug("testSearchEnglishVocabularyNullOffset: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, null);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabularyNullOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/empty.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyEmpty() {

        log.debug("testSearchEnglishVocabulary: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        log.debug("testSearchEnglishVocabulary: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyEnglishContains() {

        log.debug("testSearchEnglishVocabularyEnglishContains: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 7L, 5L, 6L });

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.DAY, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, vocabularies.size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchEnglishVocabularyEnglishContains: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyEnglishStartsWith() {

        log.debug("testSearchEnglishVocabularyEnglishStartsWith: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 13L, 12L });

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_STARTS_WITH,
                        DefinitionSearchType.STARTS_WITH, VocabularyType.NUMBER, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(2, vocabularies.size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchEnglishVocabularyEnglishStartsWith: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyEnglishFullWordSingleResult() {

        log.debug("testSearchEnglishVocabularyEnglishFullWordSingleResult: Start");

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_FULL_WORD_SINGLE,
                        DefinitionSearchType.FULL_WORD, VocabularyType.DAY, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();
        Vocabulary vocabulary = vocabularies.get(0);

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(1, vocabularies.size());
        Assert.assertEquals(7L, vocabulary.getId().longValue());

        log.debug("testSearchEnglishVocabularyEnglishFullWordSingleResult: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchEnglish.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchEnglishVocabularyEnglishFullWordMultipleResults() {

        log.debug("testSearchEnglishVocabularyEnglishFullWordMultipleResults: Start");

        List<Long> expectedIds = Arrays.asList(new Long[] { 17L, 18L });

        SearchEnglishVocabularyCommand searchEnglishVocabularyCommand = (SearchEnglishVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchEnglishVocabularyCommand", TEST_ENGLISH_FULL_WORD_MULTIPLE,
                        DefinitionSearchType.FULL_WORD, VocabularyType.COLOR, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchEnglishVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(2, vocabularies.size());
        Assert.assertNull(hyePyeResponse.getVocabulary());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchEnglishVocabularyEnglishFullWordMultipleResults: End");

    }
}
