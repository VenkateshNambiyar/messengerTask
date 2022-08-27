package com.messenger.authentication.service.authenticationService;

import com.messenger.authentication.model.Authentication;
import org.json.simple.JSONObject;

/**
 * Provides the services available for authentication in messenger.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface AuthenticationService {

    /**
     * Find a particular user record.
     *
     * @param username from Authentication Model.
     * @return user details in Map
     */
    JSONObject getUserDetailsByUsername(final String username);

    /**
     * Creates a new user.
     *
     * @param authentication Model of messenger.
     * @return Success or failure message
     */
    boolean addNewUser(final Authentication authentication);

    /**
     * Updates an existing username for user.
     *
     * @param authentication Model of messenger.
     * @return Success or failure message
     */
    boolean updateUsername(final Authentication authentication);

    /**
     * Updates an existing password for a user.
     *
     * @param authentication Model of messenger.
     * @return Success or failure message
     */
    boolean updatePassword(final Authentication authentication);

    /**
     * Display the all user records.
     *
     * @return userId, username of all user in messenger
     */
    JSONObject getAllDetails();

    /**
     * Deletes a particular userprofile.
     *
     * @param userid userid of the messenger user.
     * @return Success or failure message
     */
    boolean deleteUserProfile(final long userid);
}