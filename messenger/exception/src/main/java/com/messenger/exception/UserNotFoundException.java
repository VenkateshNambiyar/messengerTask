package com.messenger.exception;

/**
 * uses custom exception UserNotFoundException
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class UserNotFoundException extends CustomException {

    public UserNotFoundException(final String exception) {
        super(exception);
    }
}