package com.interzonedev.hyepye.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for the {@link Conjugation} domain model. Makes sure all properties are accounted for in
 * {@link Conjugation.Builder}, {@link Conjugation#equals(Object)} and {@link Conjugation#hashCode()}.
 * 
 * @author mmarkarian
 */
public class ConjugationTest {

    @Test
    public void testNullNotEqual() {
        Conjugation conjugation = Conjugation.newBuilder().build();

        Assert.assertNotEquals(conjugation, null);
    }

    @Test
    public void testOtherClass() {
        Conjugation base = Conjugation.newBuilder().build();
        String other = "foo";

        Assert.assertNotEquals(base, other);
    }

    @Test
    public void testIdentityEqual() {
        Conjugation conjugation = Conjugation.newBuilder().build();

        Assert.assertEquals(conjugation, conjugation);
    }

    @Test
    public void testDefault() {
        Conjugation base = Conjugation.newBuilder().build();
        Conjugation same = Conjugation.newBuilder().build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

    @Test
    public void testId() {
        Long baseId = 1L;
        Long otherId = 2L;

        Conjugation base = Conjugation.newBuilder().setId(baseId).build();
        Conjugation same = Conjugation.newBuilder().setId(baseId).build();
        Conjugation different = Conjugation.newBuilder().setId(otherId).build();

        Assert.assertEquals(baseId, base.getId());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testVerbId() {
        Long baseVerbId = 1L;
        Long otherVerbId = 2L;

        Conjugation base = Conjugation.newBuilder().setVerbId(baseVerbId).build();
        Conjugation same = Conjugation.newBuilder().setVerbId(baseVerbId).build();
        Conjugation different = Conjugation.newBuilder().setVerbId(otherVerbId).build();

        Assert.assertEquals(baseVerbId, base.getVerbId());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testTense() {
        String baseTense = "foo";
        String otherTense = "bar";

        Conjugation base = Conjugation.newBuilder().setTense(baseTense).build();
        Conjugation same = Conjugation.newBuilder().setTense(baseTense).build();
        Conjugation different = Conjugation.newBuilder().setTense(otherTense).build();

        Assert.assertEquals(baseTense, base.getTense());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testFirstPersonSingular() {
        String baseFirstPersonSingular = "foo";
        String otherFirstPersonSingular = "bar";

        Conjugation base = Conjugation.newBuilder().setFirstPersonSingular(baseFirstPersonSingular).build();
        Conjugation same = Conjugation.newBuilder().setFirstPersonSingular(baseFirstPersonSingular).build();
        Conjugation different = Conjugation.newBuilder().setFirstPersonSingular(otherFirstPersonSingular).build();

        Assert.assertEquals(baseFirstPersonSingular, base.getFirstPersonSingular());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testSecondPersonSingular() {
        String baseSecondPersonSingular = "foo";
        String otherSecondPersonSingular = "bar";

        Conjugation base = Conjugation.newBuilder().setSecondPersonSingular(baseSecondPersonSingular).build();
        Conjugation same = Conjugation.newBuilder().setSecondPersonSingular(baseSecondPersonSingular).build();
        Conjugation different = Conjugation.newBuilder().setSecondPersonSingular(otherSecondPersonSingular).build();

        Assert.assertEquals(baseSecondPersonSingular, base.getSecondPersonSingular());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testThirdPersonSingular() {
        String baseThirdPersonSingular = "foo";
        String otherThirdPersonSingular = "bar";

        Conjugation base = Conjugation.newBuilder().setThirdPersonSingular(baseThirdPersonSingular).build();
        Conjugation same = Conjugation.newBuilder().setThirdPersonSingular(baseThirdPersonSingular).build();
        Conjugation different = Conjugation.newBuilder().setThirdPersonSingular(otherThirdPersonSingular).build();

        Assert.assertEquals(baseThirdPersonSingular, base.getThirdPersonSingular());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testFirstPersonPlural() {
        String baseFirstPersonPlural = "foo";
        String otherFirstPersonPlural = "bar";

        Conjugation base = Conjugation.newBuilder().setFirstPersonPlural(baseFirstPersonPlural).build();
        Conjugation same = Conjugation.newBuilder().setFirstPersonPlural(baseFirstPersonPlural).build();
        Conjugation different = Conjugation.newBuilder().setFirstPersonPlural(otherFirstPersonPlural).build();

        Assert.assertEquals(baseFirstPersonPlural, base.getFirstPersonPlural());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testSecondPersonPlural() {
        String baseSecondPersonPlural = "foo";
        String otherSecondPersonPlural = "bar";

        Conjugation base = Conjugation.newBuilder().setSecondPersonPlural(baseSecondPersonPlural).build();
        Conjugation same = Conjugation.newBuilder().setSecondPersonPlural(baseSecondPersonPlural).build();
        Conjugation different = Conjugation.newBuilder().setSecondPersonPlural(otherSecondPersonPlural).build();

        Assert.assertEquals(baseSecondPersonPlural, base.getSecondPersonPlural());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testThirdPersonPlural() {
        String baseThirdPersonPlural = "foo";
        String otherThirdPersonPlural = "bar";

        Conjugation base = Conjugation.newBuilder().setThirdPersonPlural(baseThirdPersonPlural).build();
        Conjugation same = Conjugation.newBuilder().setThirdPersonPlural(baseThirdPersonPlural).build();
        Conjugation different = Conjugation.newBuilder().setThirdPersonPlural(otherThirdPersonPlural).build();

        Assert.assertEquals(baseThirdPersonPlural, base.getThirdPersonPlural());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testStatus() {
        Status baseStatus = Status.APPROVED;
        Status otherStatus = Status.SUBMITTED;

        Conjugation base = Conjugation.newBuilder().setStatus(baseStatus).build();
        Conjugation same = Conjugation.newBuilder().setStatus(baseStatus).build();
        Conjugation different = Conjugation.newBuilder().setStatus(otherStatus).build();

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

        Conjugation base = Conjugation.newBuilder().setTimeCreated(baseTimeCreated).build();
        Conjugation same = Conjugation.newBuilder().setTimeCreated(baseTimeCreated).build();
        Conjugation different = Conjugation.newBuilder().setTimeCreated(otherTimeCreated).build();

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

        Conjugation base = Conjugation.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        Conjugation same = Conjugation.newBuilder().setTimeUpdated(baseTimeUpdated).build();
        Conjugation different = Conjugation.newBuilder().setTimeUpdated(otherTimeUpdated).build();

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

        Conjugation base = Conjugation.newBuilder().setCreatedBy(baseCreatedBy).build();
        Conjugation same = Conjugation.newBuilder().setCreatedBy(baseCreatedBy).build();
        Conjugation different = Conjugation.newBuilder().setCreatedBy(otherCreatedBy).build();

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

        Conjugation base = Conjugation.newBuilder().setModifiedBy(baseModifiedBy).build();
        Conjugation same = Conjugation.newBuilder().setModifiedBy(baseModifiedBy).build();
        Conjugation different = Conjugation.newBuilder().setModifiedBy(otherModifiedBy).build();

        Assert.assertEquals(baseModifiedBy, base.getModifiedBy());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testBuildFromTemplate() {
        Conjugation base = Conjugation.newBuilder().setId(1L).setVerbId(1L).setTense("tense")
                .setFirstPersonSingular("firstPersonSingular").setSecondPersonSingular("secondPersonSingular")
                .setThirdPersonSingular("thirdPersonSingular").setFirstPersonPlural("firstPersonPlural")
                .setSecondPersonPlural("secondPersonPlural").setThirdPersonPlural("thirdPersonPlural")
                .setStatus(Status.APPROVED).setTimeCreated(new Date(1L)).setTimeUpdated(new Date(2L)).setCreatedBy(1L)
                .setModifiedBy(2L).build();
        Conjugation same = Conjugation.newBuilder(base).build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

}
