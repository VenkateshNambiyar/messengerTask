package com.messenger.exception;

/**
 * uses custom exception UsernameAlreadyExistsException
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class UsernameAlreadyExistsException extends CustomException {

    public UsernameAlreadyExistsException(final String exception) {
        super(exception);
    }
}