package com.interzonedev.hyepye.service.repository;

import com.interzonedev.hyepye.model.User;

/**
 * Checked exception that indicates a duplicate {@link User}.
 * 
 * @author mmarkarian
 */
public class DuplicateUserException extends Exception {

    private static final long serialVersionUID = 7828265323575536764L;

    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String message) {
        super(message);
    }

    public DuplicateUserException(Throwable cause) {
        super(cause);
    }

    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
