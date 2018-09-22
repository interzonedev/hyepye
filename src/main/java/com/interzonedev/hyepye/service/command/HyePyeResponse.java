package com.interzonedev.hyepye.service.command;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.IZCommandResponse;
import com.interzonedev.hyepye.model.Conjugation;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Verb;
import com.interzonedev.hyepye.model.Vocabulary;

/**
 * Value object for all {@link HyePyeCommand} respones.
 * 
 * @author mmarkarian
 */
public class HyePyeResponse implements IZCommandResponse, Serializable {

    private static final long serialVersionUID = 7125535053959188152L;

    private final Exception processingError;

    private final ValidationException validationError;

    private final User user;

    private final List<User> users;

    private final Conjugation conjugation;

    private final List<Conjugation> conjugations;

    private final Verb verb;

    private final List<Verb> verbs;

    private final Vocabulary vocabulary;

    private final List<Vocabulary> vocabularies;

    private final Integer numberOfPages;

    private final Integer returnedPageNumber;

    /**
     * Creates a new {@link HyePyeResponse} from the values set on the specified {@link Builder}.
     * 
     * @param builder The {@link Builder} that holds the values for the {@link HyePyeResponse} to create.
     */
    private HyePyeResponse(Builder builder) {
        this.processingError = builder.processingError;
        this.validationError = builder.validationError;
        this.user = builder.user;
        if (null == builder.users) {
            this.users = ImmutableList.of();
        } else {
            this.users = ImmutableList.copyOf(builder.users);
        }
        this.conjugation = builder.conjugation;
        if (null == builder.conjugations) {
            this.conjugations = ImmutableList.of();
        } else {
            this.conjugations = ImmutableList.copyOf(builder.conjugations);
        }
        this.verb = builder.verb;
        if (null == builder.verbs) {
            this.verbs = ImmutableList.of();
        } else {
            this.verbs = ImmutableList.copyOf(builder.verbs);
        }
        this.vocabulary = builder.vocabulary;
        if (null == builder.vocabularies) {
            this.vocabularies = ImmutableList.of();
        } else {
            this.vocabularies = ImmutableList.copyOf(builder.vocabularies);
        }
        this.numberOfPages = builder.numberOfPages;
        this.returnedPageNumber = builder.returnedPageNumber;
    }

    /**
     * Gets a {@link Builder} with default initial values.
     * 
     * @return Returns a {@link Builder} with default initial values.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets a {@link Builder} with initial values set from the specified template {@link HyePyeResponse}
     * 
     * @param template A {@link HyePyeResponse} from which to get the initial values for the new {@link Builder}.
     * 
     * @return Returns a {@link Builder} with initial values set from the specified template {@link HyePyeResponse}
     */
    public static Builder newBuilder(HyePyeResponse template) {
        return new Builder(template);
    }

    public Exception getProcessingError() {
        return processingError;
    }

    public ValidationException getValidationError() {
        return validationError;
    }

    public User getUser() {
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public Conjugation getConjugation() {
        return conjugation;
    }

    public List<Conjugation> getConjugations() {
        return conjugations;
    }

    public Verb getVerb() {
        return verb;
    }

    public List<Verb> getVerbs() {
        return verbs;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Integer getReturnedPageNumber() {
        return returnedPageNumber;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HyePyeResponse)) {
            return false;
        }

        HyePyeResponse that = (HyePyeResponse) obj;

        return Objects.equal(getProcessingError(), that.getProcessingError())
                && Objects.equal(getValidationError(), that.getValidationError())
                && Objects.equal(getUser(), that.getUser()) && Objects.equal(getUsers(), that.getUsers())
                && Objects.equal(getConjugation(), that.getConjugation())
                && Objects.equal(getConjugations(), that.getConjugations()) && Objects.equal(getVerb(), that.getVerb())
                && Objects.equal(getVerbs(), that.getVerbs()) && Objects.equal(getVocabulary(), that.getVocabulary())
                && Objects.equal(getVocabularies(), that.getVocabularies())
                && Objects.equal(getNumberOfPages(), that.getNumberOfPages())
                && Objects.equal(getReturnedPageNumber(), that.getReturnedPageNumber());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProcessingError(), getValidationError(), getUser(), getUsers(), getConjugation(),
                getConjugations(), getVerb(), getVerbs(), getVocabulary(), getVocabularies(), getNumberOfPages(),
                getReturnedPageNumber());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                .add("processingError", getProcessingError()).add("validationError", getValidationError())
                .add("user", getUser()).add("users", getUsers()).add("conjugation", getConjugation())
                .add("conjugations", getConjugations()).add("verb", getVerb()).add("verbs", getVerbs())
                .add("vocabulary", getVocabulary()).add("vocabularies", getVocabularies())
                .add("numberOfPages", getNumberOfPages()).add("returnedPageNumber", getReturnedPageNumber()).toString();
    }

    @Override
    public boolean hasValidationError() {
        return (null != validationError);
    }

    @Override
    public boolean hasProcessingError() {
        return (null != processingError);
    }

    /**
     * Mutable builder for creating instances of {@link HyePyeResponse}. Allows for the individual setting of
     * properties.
     * 
     * @author mmarkarian
     */
    public static class Builder {

        private Exception processingError;

        private ValidationException validationError;

        private User user;

        private List<User> users;

        private Conjugation conjugation;

        private List<Conjugation> conjugations;

        private Verb verb;

        private List<Verb> verbs;

        private Vocabulary vocabulary;

        private List<Vocabulary> vocabularies;

        private Integer numberOfPages;

        private Integer returnedPageNumber;

        /**
         * Default constructor. Allows for building a {@link HyePyeResponse} starting with default values.
         */
        private Builder() {
        }

        /**
         * Allows for building a {@link HyePyeResponse} starting with initial values set from the specified template
         * {@link HyePyeResponse}.
         * 
         * @param template A {@link HyePyeResponse} from which to get the initial values for building a new
         *            {@link HyePyeResponse}.
         */
        private Builder(HyePyeResponse template) {
            this.processingError = template.getProcessingError();
            this.validationError = template.getValidationError();
            this.user = template.getUser();
            this.users = template.getUsers();
            this.conjugation = template.getConjugation();
            this.conjugations = template.getConjugations();
            this.verb = template.getVerb();
            this.verbs = template.getVerbs();
            this.vocabulary = template.getVocabulary();
            this.vocabularies = template.getVocabularies();
            this.numberOfPages = template.getNumberOfPages();
            this.returnedPageNumber = template.getReturnedPageNumber();
        }

        /**
         * Creates a new {@link HyePyeResponse} from the values set on this {@link Builder}.
         * 
         * @return Returns a new {@link HyePyeResponse} from the values set on this {@link Builder}.
         */
        public HyePyeResponse build() {
            return new HyePyeResponse(this);
        }

        public Builder setProcessingError(Exception processingError) {
            this.processingError = processingError;
            return this;
        }

        public Builder setValidationError(ValidationException validationError) {
            this.validationError = validationError;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public Builder setConjugation(Conjugation conjugation) {
            this.conjugation = conjugation;
            return this;
        }

        public Builder setConjugations(List<Conjugation> conjugations) {
            this.conjugations = conjugations;
            return this;
        }

        public Builder setVerb(Verb verb) {
            this.verb = verb;
            return this;
        }

        public Builder setVerbs(List<Verb> verbs) {
            this.verbs = verbs;
            return this;
        }

        public Builder setVocabulary(Vocabulary vocabulary) {
            this.vocabulary = vocabulary;
            return this;
        }

        public Builder setVocabularies(List<Vocabulary> vocabularies) {
            this.vocabularies = vocabularies;
            return this;
        }

        public Builder setNumberOfPages(Integer numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }

        public Builder setReturnedPageNumber(Integer returnedPageNumber) {
            this.returnedPageNumber = returnedPageNumber;
            return this;
        }

    }

}
