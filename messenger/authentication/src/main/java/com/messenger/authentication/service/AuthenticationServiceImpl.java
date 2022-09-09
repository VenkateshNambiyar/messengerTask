package com.messenger.authentication.service;

import java.util.Collection;
import java.util.Map;

import com.messenger.authentication.dao.AuthenticationDAO;
import com.messenger.authentication.model.UserDetail;

/**
 * Provide implementation for Authentication
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final AuthenticationDAO AUTHENTICATION_DAO = new AuthenticationDAO();

    /**
     * Obtain a specific user record
     *
     * @param userId represent a UserDetail model object
     * @return information about the specified user's userName and userId
     */
    @Override
    public Collection<Map<String, Object>> getUserDetailsByUserName(final long userId) {
        return AUTHENTICATION_DAO.getUserDetailsByUserName(userId);
    }

    /**
     * Insert a new user
     *
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean addNewUser(final String tableName, final Map<String,Object> userDetail) {
        return AUTHENTICATION_DAO.addNewUser(tableName, userDetail);
    }

    /**
     * Changes a user's current userName
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean updateUserName(final String primaryKey, final String tableName, final UserDetail userDetail) {
        return AUTHENTICATION_DAO.updateUsername(primaryKey, tableName, userDetail);
    }

    /**
     * Changes a user's current password
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean updatePassword(final String primaryKey, final String tableName, final UserDetail userDetail) {
        return AUTHENTICATION_DAO.updatePassword(primaryKey, tableName, userDetail);
    }

    /**
     * Show every user record available
     *
     * @return information about the userId and userName of every user
     */
    @Override
    public Collection<Map<String, Object>> getAllDetails() {
        return AUTHENTICATION_DAO.getAllDetails();
    }

    /**
     * Removes a specific user profile
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userId     represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean deleteUserProfile(final String primaryKey, final String tableName, final long userId) {
        return AUTHENTICATION_DAO.deleteUserProfile(primaryKey, tableName, userId);
    }
}