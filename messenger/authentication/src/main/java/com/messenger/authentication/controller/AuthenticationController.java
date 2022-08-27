package com.messenger.authentication.controller;

import com.messenger.authentication.model.Authentication;
import com.messenger.authentication.service.authenticationServiceImplementation.AuthenticationServiceImplementation;
import org.json.simple.JSONObject;

/**
 * provides a services for Messenger
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationController {

    private static final AuthenticationServiceImplementation AUTHENTICATION_SERVICE =
            new AuthenticationServiceImplementation();

    /**
     * find a particular user record.
     *
     * @param username from Authentication Model.
     */
    public JSONObject getUserDetailsByUsername(final String username) {
        return AUTHENTICATION_SERVICE.getUserDetailsByUsername(username);
    }

    /**
     * Display the all user records.
     *
     * @return userId, username of all user in messenger
     */
    public JSONObject getAllDetails() {
        return AUTHENTICATION_SERVICE.getAllDetails();
    }

    /**
     * Updates an existing username for user.
     *
     * @param authentication Model object
     * @return Success or failure message
     */
    public boolean addNewUser(final Authentication authentication) {
        return AUTHENTICATION_SERVICE.addNewUser(authentication);
    }

    /**
     * Updates an existing password for a user.
     *
     * @param authentication Model object.
     * @return Success or failure message
     */
    public boolean updatePassword(final Authentication authentication) {
        return AUTHENTICATION_SERVICE.updatePassword(authentication);
    }

    /**
     * Updates an existing username for user.
     *
     * @param authentication Model object.
     * @return Success or failure message
     */
    public boolean updateUsername(final Authentication authentication) {
        return AUTHENTICATION_SERVICE.updateUsername(authentication);
    }

    /**
     * Deletes a particular userprofile.
     *
     * @param userid userid of the messenger user.
     * @return Success or failure message
     */
    public boolean deleteUserProfile(final long userid) {
        return AUTHENTICATION_SERVICE.deleteUserProfile(userid);
    }
}