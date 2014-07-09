package com.interzonedev.hyepye.service.repository.user;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.ValidationException;

/**
 * Subclass of {@link InvalidUserException} that indicates a {@link User} instance in an invalid state.
 * 
 * @author mmarkarian
 */
public class InvalidUserException extends ValidationException {

    private static final long serialVersionUID = -647048338442897441L;

    private final Set<ConstraintViolation<User>> errors;

    public InvalidUserException(Set<ConstraintViolation<User>> errors) {
        super();
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidUserException(String message, Set<ConstraintViolation<User>> errors) {
        super(message);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidUserException(Throwable t, Set<ConstraintViolation<User>> errors) {
        super(t);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidUserException(String message, Throwable t, Set<ConstraintViolation<User>> errors) {
        super(message, t);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public InvalidUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
            Set<ConstraintViolation<User>> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = Collections.unmodifiableSet(errors);
    }

    public Set<ConstraintViolation<User>> getErrors() {
        return errors;
    }

}