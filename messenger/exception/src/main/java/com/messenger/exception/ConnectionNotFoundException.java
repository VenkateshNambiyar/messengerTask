package com.messenger.exception;

/**
 * Create an exception for DataBaseConnection and it extends CustomException
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ConnectionNotFoundException extends CustomException {

    public ConnectionNotFoundException(final String exception) {
        super(exception);
    }
}