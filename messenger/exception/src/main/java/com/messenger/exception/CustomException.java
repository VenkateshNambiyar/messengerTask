package com.messenger.exception;

/**
 * Create a customException and its extends a RuntimeException
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class CustomException extends RuntimeException {

    public CustomException(final String exception) {
        super(exception);
    }
}