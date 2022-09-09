package com.messenger.authentication.view;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;

import com.messenger.validation.UpdateUserName;
import com.messenger.validation.UpdateUserPassword;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.HashMap;

import com.messenger.validation.userDetails;
import com.messenger.authentication.model.UserInformationTable;
import com.messenger.validation.AddNewUser;
import com.messenger.validation.UserDetailValidation;
import com.messenger.authentication.model.UserDetail;
import com.messenger.authentication.controller.AuthenticationController;

/**
 * Provide implementation for MessengerAuthentication
 *
 * @author Venkatesh N
 * @version 1.0
 */
@Path("/")
public class AuthenticationView extends AuthenticationController {

    /**
     * Obtain a specific user record
     *
     * @param userId represent a UserDetail model object
     * @return information about the specified user's userName and userId
     */
    @Path("/userProfile/{userId}")
    @Produces("application/json")
    @GET
    public JSONObject getUserProfile(@PathParam("userId") final long userId) {
        final Map<String, Object> result = new HashMap<>();
        final UserDetail userDetail = new UserDetail();

        userDetail.setUserId(userId);
        final String validationResult = UserDetailValidation.validateUserDetail(userDetail, userDetails.class);

        if (validationResult.equals("valid")) {
            result.put("result", super.getUserDetailsByUsername(userId));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }

    /**
     * Show every user record available
     *
     * @return information about the userId and userName of every user
     */
    @Path("/allUserDetails")
    @Produces("application/json")
    @GET
    public JSONObject getUserDetails() {
        final Map<String, Object> result = new HashMap<>();

        result.put("status", super.getAllDetails().toString());
        return new JSONObject(result);
    }

    /**
     * Insert a new user
     *
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Path("/addNewUser")
    @Produces("application/json")
    @POST
    public JSONObject addUser(final UserDetail userDetail) {
        final String validationMessage = UserDetailValidation.validateUserDetail(userDetail, AddNewUser.class);
        final Map<String, Object> userInformation = new HashMap<>();
        final Map<String, Object> result = new HashMap<>();

        userInformation.put("user_name", userDetail.getUserName());
        userInformation.put("password", userDetail.getPassword());

        if (validationMessage.equals("valid")) {
            result.put("status", super.addNewUser(UserInformationTable.tableName.name, userInformation));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Changes a user's current password
     *
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Path("/updatePassword")
    @Produces("application/json")
    @PUT
    public JSONObject updateUserPassword(final UserDetail userDetail) {
        final String validationMessage = UserDetailValidation.validateUserDetail(userDetail, UpdateUserPassword.class);
        final Map<String, Object> result = new HashMap<>();

        if (validationMessage.equals("valid")) {
            result.put("status", super.updatePassword(UserInformationTable.primaryKey.name,
                    UserInformationTable.tableName.name, userDetail));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Changes a user's current userName
     *
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Path("/updateUserName")
    @Produces("application/json")
    @PUT
    public JSONObject updateUserName(final UserDetail userDetail) {
        final String validationMessage = UserDetailValidation.validateUserDetail(userDetail, UpdateUserName.class);
        final Map<String, Object> result = new HashMap<>();

        if (validationMessage.equals("valid")) {
            result.put("status", super.updateUserName(UserInformationTable.primaryKey.name,
                    UserInformationTable.tableName.name, userDetail));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Removes a specific user profile
     *
     * @param userId     represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Path("/deleteUser/{userId}")
    @Produces("application/json")
    @DELETE
    public JSONObject deleteUserDetailsByUserId(@PathParam("userId") final long userId) {
        final Map<String, Object> result = new HashMap<>();
        final UserDetail userDetail = new UserDetail();

        userDetail.setUserId(userId);
        final String validationResult = UserDetailValidation.validateUserDetail(userDetail, userDetails.class);

        if (validationResult.equals("valid")) {
            result.put("result", super.deleteUserProfile(UserInformationTable.primaryKey.name,
                    UserInformationTable.tableName.name, userId));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}