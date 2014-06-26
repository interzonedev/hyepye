package com.interzonedev.hyepye.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for the {@link Verb} domain model. Makes sure all properties are accounted for in {@link Verb.Builder},
 * {@link Verb#equals(Object)} and {@link Verb#hashCode()}.
 * 
 * @author mmarkarian
 */
public class VerbTest {

    @Test
    public void testNullNotEqual() {
        Verb verb = Verb.newBuilder().build();

        Assert.assertNotEquals(verb, null);
    }

    @Test
    public void testIdentityEqual() {
        Verb verb = Verb.newBuilder().build();

        Assert.assertEquals(verb, verb);
    }

    @Test
    public void testDefault() {
        Verb base = Verb.newBuilder().build();
        Verb same = Verb.newBuilder().build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

    @Test
    public void testId() {
        Long baseId = 1L;
        Long otherId = 2L;

        Verb base = Verb.newBuilder().setId(baseId).build();
        Verb same = Verb.newBuilder().setId(baseId).build();
        Verb different = Verb.newBuilder().setId(otherId).build();

        Assert.assertEquals(baseId, base.getId());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testArmenian() {
        String baseArmenian = "foo";
        String otherArmenian = "bar";

        Verb base = Verb.newBuilder().setArmenian(baseArmenian).build();
        Verb same = Verb.newBuilder().setArmenian(baseArmenian).build();
        Verb different = Verb.newBuilder().setArmenian(otherArmenian).build();

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

        Verb base = Verb.newBuilder().setEnglish(baseEnglish).build();
        Verb same = Verb.newBuilder().setEnglish(baseEnglish).build();
        Verb different = Verb.newBuilder().setEnglish(otherEnglish).build();

        Assert.assertEquals(baseEnglish, base.getEnglish());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testConjugationGroup() {
        String baseConjugationGroup = "foo";
        String otherConjugationGroup = "bar";

        Verb base = Verb.newBuilder().setConjugationGroup(baseConjugationGroup).build();
        Verb same = Verb.newBuilder().setConjugationGroup(baseConjugationGroup).build();
        Verb different = Verb.newBuilder().setConjugationGroup(otherConjugationGroup).build();

        Assert.assertEquals(baseConjugationGroup, base.getConjugationGroup());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testIrregular() {
        boolean baseIrregular = true;
        boolean otherIrregular = false;

        Verb base = Verb.newBuilder().setIrregular(baseIrregular).build();
        Verb same = Verb.newBuilder().setIrregular(baseIrregular).build();
        Verb different = Verb.newBuilder().setIrregular(otherIrregular).build();

        Assert.assertEquals(baseIrregular, base.isIrregular());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testStatus() {
        Status baseStatus = Status.APPROVED;
        Status otherStatus = Status.SUBMITTED;

        Verb base = Verb.newBuilder().setStatus(baseStatus).build();
        Verb same = Verb.newBuilder().setStatus(baseStatus).build();
        Verb different = Verb.newBuilder().setStatus(otherStatus).build();

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

        Verb base = Verb.newBuilder().setTimeCreated(baseTimeCreated).build();
        Verb same = Verb.newBuilder().setTimeCreated(baseTimeCreated).build();
        Verb different = Verb.newBuilder().setTimeCreated(otherTimeCreated).build();

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

        Verb base = Verb.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        Verb same = Verb.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        Verb different = Verb.newBuilder().setTimeUpdated(otherTimeUpdated).build();

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

        Verb base = Verb.newBuilder().setCreatedBy(baseCreatedBy).build();
        Verb same = Verb.newBuilder().setCreatedBy(baseCreatedBy).build();
        Verb different = Verb.newBuilder().setCreatedBy(otherCreatedBy).build();

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

        Verb base = Verb.newBuilder().setModifiedBy(baseModifiedBy).build();
        Verb same = Verb.newBuilder().setModifiedBy(baseModifiedBy).build();
        Verb different = Verb.newBuilder().setModifiedBy(otherModifiedBy).build();

        Assert.assertEquals(baseModifiedBy, base.getModifiedBy());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testBuildFromTemplate() {

        Verb base = Verb.newBuilder().setId(1L).setArmenian("armenian").setEnglish("english")
                .setConjugationGroup("conjugationGroup").setIrregular(true).setStatus(Status.APPROVED)
                .setTimeCreated(new Date(1L)).setTimeUpdated(new Date(2L)).setCreatedBy(1L).setModifiedBy(2L).build();
        Verb same = Verb.newBuilder(base).build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

}
