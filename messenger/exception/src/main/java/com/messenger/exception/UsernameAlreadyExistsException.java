package com.messenger.exception;

/**
 * Create an exception for userName already exists and its extends CustomException
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class UsernameAlreadyExistsException extends CustomException {

    public UsernameAlreadyExistsException(final String exception) {
        super(exception);
    }
}