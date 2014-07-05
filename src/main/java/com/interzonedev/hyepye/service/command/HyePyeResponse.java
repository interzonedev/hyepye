package com.interzonedev.hyepye.service.command;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Objects;
import com.interzonedev.hyepye.model.Conjugation;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Verb;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;

/**
 * Value object for all {@link HyePyeCommand} respones.
 * 
 * @author mmarkarian
 */
public class HyePyeResponse implements Serializable {

    private static final long serialVersionUID = 7125535053959188152L;

    private final Throwable processingError;

    private final ValidationException validationError;

    private final User user;

    private final List<User> users;

    private final Conjugation conjugation;

    private final List<Conjugation> conjugations;

    private final Verb verb;

    private final Vocabulary vocabulary;

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
            this.users = Collections.emptyList();
        } else {
            this.users = Collections.unmodifiableList(builder.users);
        }
        this.conjugation = builder.conjugation;
        if (null == builder.conjugations) {
            this.conjugations = Collections.emptyList();
        } else {
            this.conjugations = Collections.unmodifiableList(builder.conjugations);
        }
        this.verb = builder.verb;
        this.vocabulary = builder.vocabulary;
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

    public Throwable getProcessingError() {
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

    public Vocabulary getVocabulary() {
        return vocabulary;
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
                && Objects.equal(getVocabulary(), that.getVocabulary());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProcessingError(), getValidationError(), getUser(), getUsers(), getConjugation(),
                getConjugations(), getVerb(), getVocabulary());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("hashCode", Integer.toHexString(hashCode()))
                .add("processingError", getProcessingError()).add("validationError", getValidationError())
                .add("user", getUser()).add("users", getUsers()).add("conjugation", getConjugation())
                .add("conjugations", getConjugations()).add("verb", getVerb()).add("vocabulary", getVocabulary())
                .toString();
    }

    /**
     * Mutable builder for creating instances of {@link HyePyeResponse}. Allows for the individual setting of
     * properties.
     * 
     * @author mmarkarian
     */
    public static class Builder {

        private Throwable processingError;

        private ValidationException validationError;

        private User user;

        private List<User> users;

        private Conjugation conjugation;

        private List<Conjugation> conjugations;

        private Verb verb;

        private Vocabulary vocabulary;

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
            this.vocabulary = template.getVocabulary();
        }

        /**
         * Creates a new {@link HyePyeResponse} from the values set on this {@link Builder}.
         * 
         * @return Returns a new {@link HyePyeResponse} from the values set on this {@link Builder}.
         */
        public HyePyeResponse build() {
            return new HyePyeResponse(this);
        }

        public Builder setProcessingError(Throwable processingError) {
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

        public Builder setVocabulary(Vocabulary vocabulary) {
            this.vocabulary = vocabulary;
            return this;
        }

    }

}
