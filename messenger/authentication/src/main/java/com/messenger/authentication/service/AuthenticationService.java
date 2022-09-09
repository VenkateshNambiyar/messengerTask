package com.messenger.authentication.service;

import java.util.Collection;
import java.util.Map;

import com.messenger.authentication.model.UserDetail;

/**
 * Provides the authentication services that Messenger has available
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface AuthenticationService {

    /**
     * Obtain a specific user record
     *
     * @param userId represent a UserDetail model object
     * @return information about the specified user's userName and userId
     */
    Collection<Map<String, Object>> getUserDetailsByUserName(final long userId);

    /**
     * Insert a new user
     *
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    Boolean addNewUser(final String tableName, final Map<String, Object> userDetail);

    /**
     * Changes a user's current userName
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    Boolean updateUserName(final String primaryKey, final String tableName, final UserDetail userDetail);

    /**
     * Changes a user's current password
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    Boolean updatePassword(final String primaryKey, final String tableName, final UserDetail userDetail);

    /**
     * Show every user record available
     *
     * @return information about the userId and userName of every user
     */
    Collection<Map<String, Object>> getAllDetails();

    /**
     * Removes a specific user profile
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userId     represent a UserDetail model object
     * @return message of Success or Failure
     */
    Boolean deleteUserProfile(final String primaryKey, final String tableName, final long userId);
}