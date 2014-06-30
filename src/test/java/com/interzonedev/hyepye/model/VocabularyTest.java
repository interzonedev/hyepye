package com.interzonedev.hyepye.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for the {@link Vocabulary} domain model. Makes sure all properties are accounted for in
 * {@link Vocabulary.Builder}, {@link Vocabulary#equals(Object)} and {@link Vocabulary#hashCode()}.
 * 
 * @author mmarkarian
 */
public class VocabularyTest {

    @Test
    public void testNullNotEqual() {
        Vocabulary vocabulary = Vocabulary.newBuilder().build();

        Assert.assertNotEquals(vocabulary, null);
    }

    @Test
    public void testOtherClass() {
        Vocabulary base = Vocabulary.newBuilder().build();
        String other = "foo";

        Assert.assertNotEquals(base, other);
    }

    @Test
    public void testIdentityEqual() {
        Vocabulary vocabulary = Vocabulary.newBuilder().build();

        Assert.assertEquals(vocabulary, vocabulary);
    }

    @Test
    public void testDefault() {
        Vocabulary base = Vocabulary.newBuilder().build();
        Vocabulary same = Vocabulary.newBuilder().build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

    @Test
    public void testId() {
        Long baseId = 1L;
        Long otherId = 2L;

        Vocabulary base = Vocabulary.newBuilder().setId(baseId).build();
        Vocabulary same = Vocabulary.newBuilder().setId(baseId).build();
        Vocabulary different = Vocabulary.newBuilder().setId(otherId).build();

        Assert.assertEquals(baseId, base.getId());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testArmenian() {
        String baseArmenian = "բարի";
        String otherArmenian = "եկաք";

        Vocabulary base = Vocabulary.newBuilder().setArmenian(baseArmenian).build();
        Vocabulary same = Vocabulary.newBuilder().setArmenian(baseArmenian).build();
        Vocabulary different = Vocabulary.newBuilder().setArmenian(otherArmenian).build();

        Assert.assertEquals(baseArmenian, base.getArmenian());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testEnglish() {
        String baseEnglish = "foo";
        String otherEnglish = "bar";

        Vocabulary base = Vocabulary.newBuilder().setEnglish(baseEnglish).build();
        Vocabulary same = Vocabulary.newBuilder().setEnglish(baseEnglish).build();
        Vocabulary different = Vocabulary.newBuilder().setEnglish(otherEnglish).build();

        Assert.assertEquals(baseEnglish, base.getEnglish());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testVocabularyType() {
        VocabularyType baseVocabularyType = VocabularyType.NOUN;
        VocabularyType otherVocabularyType = VocabularyType.NUMBER;

        Vocabulary base = Vocabulary.newBuilder().setVocabularyType(baseVocabularyType).build();
        Vocabulary same = Vocabulary.newBuilder().setVocabularyType(baseVocabularyType).build();
        Vocabulary different = Vocabulary.newBuilder().setVocabularyType(otherVocabularyType).build();

        Assert.assertEquals(baseVocabularyType, base.getVocabularyType());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testStatus() {
        Status baseStatus = Status.APPROVED;
        Status otherStatus = Status.SUBMITTED;

        Vocabulary base = Vocabulary.newBuilder().setStatus(baseStatus).build();
        Vocabulary same = Vocabulary.newBuilder().setStatus(baseStatus).build();
        Vocabulary different = Vocabulary.newBuilder().setStatus(otherStatus).build();

        Assert.assertEquals(baseStatus, base.getStatus());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testTimeCreated() {
        Date baseTimeCreated = new Date();
        Date otherTimeCreated = new Date(1L);

        Vocabulary base = Vocabulary.newBuilder().setTimeCreated(baseTimeCreated).build();
        Vocabulary same = Vocabulary.newBuilder().setTimeCreated(baseTimeCreated).build();
        Vocabulary different = Vocabulary.newBuilder().setTimeCreated(otherTimeCreated).build();

        Assert.assertEquals(baseTimeCreated, base.getTimeCreated());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testTimeUpdated() {
        Date baseTimeUpdated = new Date();
        Date otherTimeUpdated = new Date(1L);

        Vocabulary base = Vocabulary.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        Vocabulary same = Vocabulary.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        Vocabulary different = Vocabulary.newBuilder().setTimeUpdated(otherTimeUpdated).build();

        Assert.assertEquals(baseTimeUpdated, base.getTimeUpdated());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testCreatedBy() {
        Long baseCreatedBy = 1L;
        Long otherCreatedBy = 2L;

        Vocabulary base = Vocabulary.newBuilder().setCreatedBy(baseCreatedBy).build();
        Vocabulary same = Vocabulary.newBuilder().setCreatedBy(baseCreatedBy).build();
        Vocabulary different = Vocabulary.newBuilder().setCreatedBy(otherCreatedBy).build();

        Assert.assertEquals(baseCreatedBy, base.getCreatedBy());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testModifiedBy() {
        Long baseModifiedBy = 1L;
        Long otherModifiedBy = 2L;

        Vocabulary base = Vocabulary.newBuilder().setModifiedBy(baseModifiedBy).build();
        Vocabulary same = Vocabulary.newBuilder().setModifiedBy(baseModifiedBy).build();
        Vocabulary different = Vocabulary.newBuilder().setModifiedBy(otherModifiedBy).build();

        Assert.assertEquals(baseModifiedBy, base.getModifiedBy());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testBuildFromTemplate() {

        Vocabulary base = Vocabulary.newBuilder().setId(1L).setArmenian("armenian").setEnglish("english")
                .setVocabularyType(VocabularyType.NOUN).setStatus(Status.APPROVED).setTimeCreated(new Date(1L))
                .setTimeUpdated(new Date(2L)).setCreatedBy(1L).setModifiedBy(2L).build();
        Vocabulary same = Vocabulary.newBuilder(base).build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

}
