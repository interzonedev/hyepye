package com.interzonedev.hyepye.service.repository;

import com.interzonedev.hyepye.service.ValidationException;

/**
 * Subclass of {@link ValidationException} that indicates a duplicate model instance.
 * 
 * @author mmarkarian
 */
public class DuplicateModelException extends ValidationException {

    private static final long serialVersionUID = 7828265323575536764L;

    public DuplicateModelException() {
        super();
    }

    public DuplicateModelException(String message) {
        super(message);
    }

    public DuplicateModelException(Throwable cause) {
        super(cause);
    }

    public DuplicateModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateModelException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
