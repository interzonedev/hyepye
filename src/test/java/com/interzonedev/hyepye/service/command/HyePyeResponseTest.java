package com.interzonedev.hyepye.service.command;

import org.junit.Assert;
import org.junit.Test;

import com.interzonedev.hyepye.model.Conjugation;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Verb;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;

/**
 * Unit test for the {@link HyePyeResponse} domain model. Makes sure all properties are accounted for in
 * {@link HyePyeResponse.Builder}, {@link HyePyeResponse#equals(Object)} and {@link HyePyeResponse#hashCode()}.
 * 
 * @author mmarkarian
 */
public class HyePyeResponseTest {

    @Test
    public void testNullNotEqual() {
        HyePyeResponse user = HyePyeResponse.newBuilder().build();

        Assert.assertNotEquals(user, null);
    }

    @Test
    public void testOtherClass() {
        HyePyeResponse base = HyePyeResponse.newBuilder().build();
        String other = "foo";

        Assert.assertNotEquals(base, other);
    }

    @Test
    public void testIdentityEqual() {
        HyePyeResponse user = HyePyeResponse.newBuilder().build();

        Assert.assertEquals(user, user);
    }

    @Test
    public void testDefault() {
        HyePyeResponse base = HyePyeResponse.newBuilder().build();
        HyePyeResponse same = HyePyeResponse.newBuilder().build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

    @Test
    public void testProcessingError() {
        Throwable baseProcessingError = new RuntimeException("foo");
        Throwable otherProcessingError = new RuntimeException("bar");

        HyePyeResponse base = HyePyeResponse.newBuilder().setProcessingError(baseProcessingError).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setProcessingError(baseProcessingError).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setProcessingError(otherProcessingError).build();

        Assert.assertEquals(baseProcessingError, base.getProcessingError());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testValidationException() {
        ValidationException baseValidationError = new ValidationException("foo");
        ValidationException otherValidationError = new ValidationException("bar");

        HyePyeResponse base = HyePyeResponse.newBuilder().setValidationError(baseValidationError).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setValidationError(baseValidationError).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setValidationError(otherValidationError).build();

        Assert.assertEquals(baseValidationError, base.getValidationError());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testUser() {
        User baseUser = User.newBuilder().setId(1L).build();
        User otherUser = User.newBuilder().setId(2L).build();

        HyePyeResponse base = HyePyeResponse.newBuilder().setUser(baseUser).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setUser(baseUser).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setUser(otherUser).build();

        Assert.assertEquals(baseUser, base.getUser());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testConjugation() {
        Conjugation baseConjugation = Conjugation.newBuilder().setId(1L).build();
        Conjugation otherConjugation = Conjugation.newBuilder().setId(2L).build();

        HyePyeResponse base = HyePyeResponse.newBuilder().setConjugation(baseConjugation).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setConjugation(baseConjugation).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setConjugation(otherConjugation).build();

        Assert.assertEquals(baseConjugation, base.getConjugation());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testVerb() {
        Verb baseVerb = Verb.newBuilder().setId(1L).build();
        Verb otherVerb = Verb.newBuilder().setId(2L).build();

        HyePyeResponse base = HyePyeResponse.newBuilder().setVerb(baseVerb).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setVerb(baseVerb).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setVerb(otherVerb).build();

        Assert.assertEquals(baseVerb, base.getVerb());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

    @Test
    public void testVocabulary() {
        Vocabulary baseVocabulary = Vocabulary.newBuilder().setId(1L).build();
        Vocabulary otherVocabulary = Vocabulary.newBuilder().setId(2L).build();

        HyePyeResponse base = HyePyeResponse.newBuilder().setVocabulary(baseVocabulary).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setVocabulary(baseVocabulary).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setVocabulary(otherVocabulary).build();

        Assert.assertEquals(baseVocabulary, base.getVocabulary());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());
    }

}
