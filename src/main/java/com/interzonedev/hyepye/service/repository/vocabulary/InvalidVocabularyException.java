package com.interzonedev.hyepye.service.repository.vocabulary;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;

/**
 * Subclass of {@link InvalidVocabularyException} that indicates a {@link Vocabulary} instance in an invalid state.
 * 
 * @author mmarkarian
 */
public class InvalidVocabularyException extends ValidationException {

    private static final long serialVersionUID = -647048338442897441L;

    private final Set<ConstraintViolation<Vocabulary>> errors;

    public InvalidVocabularyException(Set<ConstraintViolation<Vocabulary>> errors) {
        super();
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidVocabularyException(String message, Set<ConstraintViolation<Vocabulary>> errors) {
        super(message);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidVocabularyException(Throwable t, Set<ConstraintViolation<Vocabulary>> errors) {
        super(t);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidVocabularyException(String message, Throwable t, Set<ConstraintViolation<Vocabulary>> errors) {
        super(message, t);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidVocabularyException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace, Set<ConstraintViolation<Vocabulary>> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public Set<ConstraintViolation<Vocabulary>> getErrors() {
        return errors;
    }

}
