package com.interzonedev.hyepye.service.command;

import java.util.Arrays;
import java.util.List;

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
    public void testUsers() {
        List<User> baseUsers = Arrays.asList(new User[] { User.newBuilder().setId(1L).build(),
                User.newBuilder().setId(2L).build() });
        List<User> otherUsers = Arrays.asList(new User[] { User.newBuilder().setId(3L).build(),
                User.newBuilder().setId(4L).build() });

        HyePyeResponse base = HyePyeResponse.newBuilder().setUsers(baseUsers).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setUsers(baseUsers).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setUsers(otherUsers).build();
        HyePyeResponse defaultVals = HyePyeResponse.newBuilder().build();

        Assert.assertEquals(baseUsers, base.getUsers());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());

        Assert.assertTrue(defaultVals.getUsers().isEmpty());
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
    public void testConjugations() {
        List<Conjugation> baseConjugations = Arrays.asList(new Conjugation[] {
                Conjugation.newBuilder().setId(1L).build(), Conjugation.newBuilder().setId(2L).build() });
        List<Conjugation> otherConjugations = Arrays.asList(new Conjugation[] {
                Conjugation.newBuilder().setId(3L).build(), Conjugation.newBuilder().setId(4L).build() });

        HyePyeResponse base = HyePyeResponse.newBuilder().setConjugations(baseConjugations).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setConjugations(baseConjugations).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setConjugations(otherConjugations).build();
        HyePyeResponse defaultVals = HyePyeResponse.newBuilder().build();

        Assert.assertEquals(baseConjugations, base.getConjugations());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());

        Assert.assertTrue(defaultVals.getConjugations().isEmpty());
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
    public void testVerbs() {
        List<Verb> baseVerbs = Arrays.asList(new Verb[] { Verb.newBuilder().setId(1L).build(),
                Verb.newBuilder().setId(2L).build() });
        List<Verb> otherVerbs = Arrays.asList(new Verb[] { Verb.newBuilder().setId(3L).build(),
                Verb.newBuilder().setId(4L).build() });

        HyePyeResponse base = HyePyeResponse.newBuilder().setVerbs(baseVerbs).build();
        HyePyeResponse same = HyePyeResponse.newBuilder().setVerbs(baseVerbs).build();
        HyePyeResponse different = HyePyeResponse.newBuilder().setVerbs(otherVerbs).build();
        HyePyeResponse defaultVals = HyePyeResponse.newBuilder().build();

        Assert.assertEquals(baseVerbs, base.getVerbs());
        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());

        Assert.assertNotEquals(base, different);
        Assert.assertNotEquals(base.hashCode(), different.hashCode());

        Assert.assertTrue(defaultVals.getVerbs().isEmpty());
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

    @Test
    public void testBuildFromTemplate() {
        Throwable processingError = new RuntimeException("foo");
        ValidationException validationError = new ValidationException("bar");
        User user = User.newBuilder().setId(1L).build();
        List<User> users = Arrays.asList(new User[] { User.newBuilder().setId(1L).build(),
                User.newBuilder().setId(2L).build() });
        Conjugation conjugation = Conjugation.newBuilder().setId(1L).build();
        List<Conjugation> conjugations = Arrays.asList(new Conjugation[] { Conjugation.newBuilder().setId(1L).build(),
                Conjugation.newBuilder().setId(2L).build() });
        Verb verb = Verb.newBuilder().setId(1L).build();
        List<Verb> verbs = Arrays.asList(new Verb[] { Verb.newBuilder().setId(1L).build(),
                Verb.newBuilder().setId(2L).build() });
        Vocabulary vocabulary = Vocabulary.newBuilder().setId(1L).build();

        HyePyeResponse base = HyePyeResponse.newBuilder().setProcessingError(processingError)
                .setValidationError(validationError).setUser(user).setUsers(users).setConjugation(conjugation)
                .setConjugations(conjugations).setVerb(verb).setVerbs(verbs).setVocabulary(vocabulary).build();
        HyePyeResponse same = HyePyeResponse.newBuilder(base).build();

        Assert.assertEquals(base, same);
        Assert.assertEquals(base.hashCode(), same.hashCode());
    }

}
