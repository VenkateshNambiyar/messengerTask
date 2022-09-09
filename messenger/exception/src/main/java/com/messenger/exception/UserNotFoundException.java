package com.messenger.exception;

/**
 * Create an exception for userName not found and its extends CustomException
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class UserNotFoundException extends CustomException {

    public UserNotFoundException(final String exception) {
        super(exception);
    }
}