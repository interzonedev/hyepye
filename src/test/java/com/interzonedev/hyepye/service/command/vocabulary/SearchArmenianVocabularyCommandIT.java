package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.HyepyeAbstractIT;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import com.interzonedev.zankou.dataset.DataSet;

/**
 * Integration tests for {@link SearchArmenianVocabularyCommand}.
 * 
 * @author mmarkarian
 */
public class SearchArmenianVocabularyCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(SearchArmenianVocabularyCommandIT.class);

    private static final String TEST_ARMENIAN_FULL_WORD = "երկու";
    private static final String TEST_ARMENIAN_STARTS_WITH = "երե";
    private static final String TEST_ARMENIAN_CONTAINS = "եր";

    @Test
    public void testSearchArmenianVocabularyNullArmenian() {

        log.debug("testSearchArmenianVocabularyNullArmenian: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", null, DefinitionSearchType.FULL_WORD,
                        VocabularyType.NOUN, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNullArmenian: End");

    }

    @Test
    public void testSearchArmenianVocabularyNullDefinitionSearchType() {

        log.debug("testSearchArmenianVocabularyNullDefinitionSearchType: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_FULL_WORD, null,
                        VocabularyType.NOUN, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNullDefinitionSearchType: End");

    }

    @Test
    public void testSearchArmenianVocabularyNegativeLimit() {

        log.debug("testSearchArmenianVocabularyNegativeLimit: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, Status.APPROVED, true, -1L, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNegativeLimit: End");

    }

    @Test
    public void testSearchArmenianVocabularyNegativeOffset() {

        log.debug("testSearchArmenianVocabularyNegativeOffset: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, Status.APPROVED, true, null, -1L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNotNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNegativeOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyNullVocabularyType() {

        log.debug("testSearchArmenianVocabularyNullVocabularyType: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, null, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(5, vocabularies.size());

        log.debug("testSearchArmenianVocabularyNullVocabularyType: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyNounVocabularyType() {

        log.debug("testSearchArmenianVocabularyNounVocabularyType: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(1, vocabularies.size());

        log.debug("testSearchArmenianVocabularyNounVocabularyType: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyNullStatus() {

        log.debug("testSearchArmenianVocabularyNullVocabularyType: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, null, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(2, vocabularies.size());

        log.debug("testSearchArmenianVocabularyNullStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyApprovedStatus() {

        log.debug("testSearchArmenianVocabularyApprovedStatus: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(1, vocabularies.size());

        log.debug("testSearchArmenianVocabularyApprovedStatus: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyAscending() {

        log.debug("testSearchArmenianVocabularyAscending: Start");

        Long limit = 2L;
        Long offset = 1L;

        List<Long> expectedIds = Arrays.asList(new Long[] { 11L, 13L });

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, true, limit, offset);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchArmenianVocabularyAscending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyDescending() {

        log.debug("testSearchArmenianVocabularyDescending: Start");

        Long limit = 2L;
        Long offset = 1L;

        List<Long> expectedIds = Arrays.asList(new Long[] { 11L, 12L });

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, false, limit, offset);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        List<Vocabulary> vocabularies = hyePyeResponse.getVocabularies();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());

        for (int i = 0; i < expectedIds.size(); i++) {
            Assert.assertEquals(expectedIds.get(i), vocabularies.get(i).getId());
        }

        log.debug("testSearchArmenianVocabularyDescending: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyZeroLimit() {

        log.debug("testSearchArmenianVocabularyZeroLimit: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, true, 0L, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyZeroLimit: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyNonZeroLimit() {

        log.debug("testSearchArmenianVocabularyNonZeroLimit: Start");

        Long limit = 2L;

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, true, limit, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(limit.longValue(), (long) hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNonZeroLimit: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyNullLimit() {

        log.debug("testSearchArmenianVocabularyNullLimit: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNullLimit: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyZeroOffset() {

        log.debug("testSearchArmenianVocabularyZeroOffset: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyZeroOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyNonZeroOffset() {

        log.debug("testSearchArmenianVocabularyNonZeroOffset: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, true, null, 1L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(2, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNonZeroOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/searchArmenian.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyNullOffset() {

        log.debug("testSearchArmenianVocabularyNullOffset: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NUMBER, Status.APPROVED, true, null, null);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(3, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabularyNullOffset: End");

    }

    @Test
    @DataSet(filename = "com/interzonedev/hyepye/dataset/vocabulary/empty.xml", dataSourceBeanId = "hyepye.service.dataSource")
    public void testSearchArmenianVocabularyEmpty() {

        log.debug("testSearchArmenianVocabulary: Start");

        SearchArmenianVocabularyCommand searchArmenianVocabularyCommand = (SearchArmenianVocabularyCommand) applicationContext
                .getBean("hyepye.service.searchArmenianVocabularyCommand", TEST_ARMENIAN_CONTAINS,
                        DefinitionSearchType.CONTAINS, VocabularyType.NOUN, Status.APPROVED, true, null, 0L);

        HyePyeResponse hyePyeResponse = searchArmenianVocabularyCommand.execute();

        Assert.assertNull(hyePyeResponse.getValidationError());
        Assert.assertNull(hyePyeResponse.getProcessingError());
        Assert.assertEquals(0, hyePyeResponse.getVocabularies().size());

        log.debug("testSearchArmenianVocabulary: End");

    }

}
