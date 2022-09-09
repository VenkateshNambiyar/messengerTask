package com.messenger.authentication.controller;

import java.util.Collection;
import java.util.Map;

import com.messenger.authentication.model.UserDetail;
import com.messenger.authentication.service.AuthenticationServiceImpl;

/**
 * Controls the data flow into the model object and updates the view whenever data changes
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationController {

    private static final AuthenticationServiceImpl AUTHENTICATION_SERVICE = new AuthenticationServiceImpl();

    /**
     * Obtain a specific user record
     *
     * @param userId represent a UserDetail model object
     * @return information about the specified user's userName and userId
     */
    public Collection<Map<String, Object>> getUserDetailsByUsername(final long userId) {
        return AUTHENTICATION_SERVICE.getUserDetailsByUserName(userId);
    }

    /**
     * Show every user record available
     *
     * @return information about the userId and userName of every user
     */
    public Collection<Map<String, Object>> getAllDetails() {
        return AUTHENTICATION_SERVICE.getAllDetails();
    }

    /**
     * Insert a new user
     *
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean addNewUser(final String tableName, final Map<String, Object> userDetail) {
        return AUTHENTICATION_SERVICE.addNewUser(tableName, userDetail);
    }

    /**
     * Changes a user's current password
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean updatePassword(final String primaryKey, final String tableName, final UserDetail userDetail) {
        return AUTHENTICATION_SERVICE.updatePassword(primaryKey, tableName, userDetail);
    }

    /**
     * Changes a user's current userName
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean updateUserName(final String primaryKey, final String tableName, final UserDetail userDetail) {
        return AUTHENTICATION_SERVICE.updateUserName(primaryKey, tableName, userDetail);
    }

    /**
     * Removes a specific user profile
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userId     represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean deleteUserProfile(final String primaryKey, final String tableName, final long userId) {
        return AUTHENTICATION_SERVICE.deleteUserProfile(primaryKey, tableName, userId);
    }
}