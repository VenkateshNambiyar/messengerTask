package com.messenger.authentication.view;

import java.util.*;
import javax.ws.rs.*;

import com.messenger.authentication.controller.AuthenticationController;
import com.messenger.authentication.model.Authentication;
import com.messenger.authentication.validation.implementation.AuthenticationValidation;
import org.json.simple.JSONObject;

/**
 * Provide implementation for MessengerAuthentication
 *
 * @author Venkatesh N
 * @version 1.0
 */
@Path("/")
public class AuthenticationView extends AuthenticationController {

    /**
     * Display the all user records.
     *
     * @return userId, username of all user in messenger
     */
    @Path("/allUserDetails")
    @Produces("application/json")
    @GET
    public JSONObject getUserDetails() {
        return super.getAllDetails();
    }

    /**
     * Find a particular user record.
     *
     * @param username from Authentication Model.
     * @return userId, Username of the particular user
     */
    @Path("/userProfile/{username}")
    @Produces("application/json")
    @GET
    public JSONObject getUserProfile(@PathParam("username") final String username) {
        final Map<String, Object> result = new HashMap<>();
        final String validationResult = AuthenticationValidation.checkUsername(username);

        if (validationResult.equals("Username is valid")) {
            result.put("result", super.getUserDetailsByUsername(username));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }

    /**
     * Creates a new user.
     *
     * @param authentication Model of messenger.
     * @return Success or failure message
     */
    @Path("/addNewUser")
    @Produces("application/json")
    @POST
    public JSONObject addUser(final Authentication authentication) {
        final Map<String, Object> result = new HashMap<>();
        final String usernameValidation = AuthenticationValidation.checkUsername(authentication.getUsername());
        final String passwordValidation = AuthenticationValidation.checkPassword(authentication.getPassword());

        try {
            if (usernameValidation.equals("Username is valid") & passwordValidation.equals("Password is valid")) {
                result.put("result", super.addNewUser(authentication));
            } else {
                result.put("username", usernameValidation);
                result.put("password", passwordValidation);
            }
        } catch (Exception exception) {
            result.put("result", "UserName already Exists");
        }
        return new JSONObject(result);
    }

    /**
     * Updates an existing user details.
     *
     * @param authentication Model of messenger.
     * @return Success or failure message
     */
    @Path("/updateDetails")
    @Produces("application/json")
    @PUT
    public JSONObject updateUserDetails(final Authentication authentication) {
        final Map<String, Object> result = new HashMap<>();
        final String usernameValidation = AuthenticationValidation.checkUsername(authentication.getUsername());
        final String passwordValidation = AuthenticationValidation.checkPassword(authentication.getPassword());
        final String userIdValidation = AuthenticationValidation.checkUserId(authentication.getUserId());

        try {
            if (userIdValidation.equals("UserId is valid")) {

                if (usernameValidation.equals("Username is valid")) {
                    result.put("result", super.updateUsername(authentication));
                } else {
                    result.put("userId", userIdValidation);
                    result.put("username", usernameValidation);
                }
            } else if (passwordValidation.equals("Password is valid")) {

                if (usernameValidation.equals("Username is valid")) {
                    result.put("result", super.updatePassword(authentication));
                } else {
                    result.put("username", usernameValidation);
                    result.put("password", passwordValidation);
                }
            } else {
                final StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("username should have at least contain 3 Character,");
                stringBuilder.append(" userid should have at least 1 character,");
                stringBuilder.append(" password should have at least 6 character");
                result.put("result", stringBuilder);
            }
        } catch (Exception exception) {
            result.put("result", "User Not Found");
        }
        return new JSONObject(result);
    }

    /**
     * Deletes a particular userprofile.
     *
     * @param userid userid of the messenger user.
     * @return Success or failure message
     */
    @Path("/deleteUser/{userid}")
    @Produces("application/json")
    @DELETE
    public JSONObject deleteUserDetailsByUserId(final @PathParam("userid") long userid) {
        final Map<String, Object> result = new HashMap<>();

        final String validationResult = AuthenticationValidation.checkUserId(userid);

        if (validationResult.equals("Username is valid")) {
            result.put("result", super.deleteUserProfile(userid));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}