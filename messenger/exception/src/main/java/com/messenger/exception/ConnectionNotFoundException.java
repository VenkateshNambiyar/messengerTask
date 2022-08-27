package com.messenger.exception;

/**
 * uses custom exception DatabaseNotConnectedException
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ConnectionNotFoundException extends CustomException {

    public ConnectionNotFoundException(final String exception) {
        super(exception);
    }
}