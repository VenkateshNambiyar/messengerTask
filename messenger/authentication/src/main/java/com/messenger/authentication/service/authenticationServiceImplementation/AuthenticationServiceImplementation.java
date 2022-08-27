package com.messenger.authentication.service.authenticationServiceImplementation;

import com.messenger.authentication.dao.AuthenticationDAO;
import com.messenger.authentication.model.Authentication;
import com.messenger.authentication.service.authenticationService.AuthenticationService;
import org.json.simple.JSONObject;

/**
 * Provide implementation for Authentication
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationServiceImplementation implements AuthenticationService {

    private static final AuthenticationDAO AUTHENTICATION_DAO = new AuthenticationDAO();

    /**
     * Find a particular user record.
     *
     * @param username from Authentication Model.
     * @return username, userId of a particular user
     */
    @Override
    public JSONObject getUserDetailsByUsername(String username) {
        return AUTHENTICATION_DAO.getUserDetailsByUsername(username);
    }

    /**
     * Creates a new user.
     *
     * @param authentication Model of messenger.
     * @return Success or failure message
     */
    @Override
    public boolean addNewUser(final Authentication authentication) {
        return AUTHENTICATION_DAO.addNewUser(authentication);
    }

    /**
     * Updates an existing username for user.
     *
     * @param authentication Model of messenger.
     * @return Success(true or failure message
     */
    @Override
    public boolean updateUsername(final Authentication authentication) {
        return AUTHENTICATION_DAO.updateUsername(authentication);
    }

    /**
     * Updates an existing password for a user.
     *
     * @param authentication Model of messenger.
     * @return Success or failure message
     */
    @Override
    public boolean updatePassword(final Authentication authentication) {
        return AUTHENTICATION_DAO.updatePassword(authentication);
    }

    /**
     * Display the all user records.
     *
     * @return userId, username of all user in messenger
     */
    @Override
    public JSONObject getAllDetails() {
        return AUTHENTICATION_DAO.getAllDetails();
    }

    /**
     * Deletes a particular userprofile.
     *
     * @param userid userid of the messenger user.
     * @return Success or failure message
     */
    @Override
    public boolean deleteUserProfile(final long userid) {
        return AUTHENTICATION_DAO.deleteUserProfile(userid);
    }
}