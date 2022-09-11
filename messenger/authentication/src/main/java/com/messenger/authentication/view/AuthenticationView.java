package com.messenger.authentication.view;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.messenger.orm.TableName;
import com.messenger.orm.UserLogin;
import com.messenger.validation.UpdateUserName;
import com.messenger.validation.UpdateUserPassword;
import com.messenger.validation.UserDetails;
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
public class AuthenticationView {

    private static final AuthenticationController AUTHENTICATION_CONTROLLER = new AuthenticationController();

    /**
     * Obtain a specific user record
     *
     * @param id represent a UserDetail model object
     * @return information about the specified user's userName and userId
     */
    @Path("/userProfile/{id}")
    @Produces("application/json")
    @GET
    public JSONObject getParticularDetails(@PathParam("id") final long id) {
        final Map<String, Object> result = new HashMap<>();
        final List<String> columnList = new ArrayList<>();
        final Map<String, Object> conditionColumnMap = new HashMap<>();
        final UserDetail userDetail = new UserDetail();

        userDetail.setUserId(id);
        final String validationResult = UserDetailValidation.validateDetails(userDetail, UserDetails.class);

        columnList.add(UserLogin.userId.getColumnName());
        columnList.add(UserLogin.userName.getColumnName());
        conditionColumnMap.put(UserLogin.userId.getColumnName(), userDetail.getUserId());

        if (validationResult.equals("valid")) {
            result.put("result", AUTHENTICATION_CONTROLLER.getParticularDetailsById(TableName.user_login, columnList,
                    conditionColumnMap));
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
    public JSONObject getAllDetails() {
        final Map<String, Object> result = new HashMap<>();
        final List<String> columnList = new ArrayList<>();

        columnList.add(UserLogin.userId.getColumnName());
        columnList.add(UserLogin.userName.getColumnName());
        result.put("status", AUTHENTICATION_CONTROLLER.getAllDetails(TableName.user_login, columnList));
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
        final String validationMessage = UserDetailValidation.validateDetails(userDetail, AddNewUser.class);
        final Map<String, Object> objectMap = new HashMap<>();
        final Map<String, Object> result = new HashMap<>();

        objectMap.put(UserLogin.password.getColumnName(), userDetail.getPassword());
        objectMap.put(UserLogin.userName.getColumnName(), userDetail.getUserName());

        if (validationMessage.equals("valid")) {
            result.put("status", AUTHENTICATION_CONTROLLER.addNewUser(TableName.user_login, objectMap));
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
    public JSONObject changePassword(final UserDetail userDetail) {
        final String validationMessage = UserDetailValidation.validateDetails(userDetail, UpdateUserPassword.class);
        final Map<String, Object> result = new HashMap<>();
        final Map<String, Object> conditionColumn = new HashMap<>();
        final Map<String, Object> objectMap = new HashMap<>();

        objectMap.put(UserLogin.password.getColumnName(), userDetail.getPassword());
        conditionColumn.put(UserLogin.userId.getColumnName(), userDetail.getUserId());

        if (validationMessage.equals("valid")) {
            result.put("status", AUTHENTICATION_CONTROLLER.updatePassword(TableName.user_login,
                    objectMap, conditionColumn));
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
    public JSONObject changeUserName(final UserDetail userDetail) {
        final String validationMessage = UserDetailValidation.validateDetails(userDetail, UpdateUserName.class);
        final Map<String, Object> result = new HashMap<>();
        final Map<String, Object> conditionColumn = new HashMap<>();
        final Map<String, Object> objectDetails = new HashMap<>();

        objectDetails.put(UserLogin.password.getColumnName(), userDetail.getPassword());
        conditionColumn.put(UserLogin.userId.getColumnName(), userDetail.getUserId());

        if (validationMessage.equals("valid")) {
            result.put("status", AUTHENTICATION_CONTROLLER.updateUserName(TableName.user_login, objectDetails,
                    conditionColumn));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Removes a specific user profile
     *
     * @param id represent a UserDetail model object
     * @return message of Success or Failure
     */
    @Path("/deleteUser/{id}")
    @Produces("application/json")
    @DELETE
    public JSONObject deleteDetails(@PathParam("id") final long id) {
        final Map<String, Object> result = new HashMap<>();
        final UserDetail userDetail = new UserDetail();
        final Map<String, Object> objectDetails = new HashMap<>();

        userDetail.setUserId(id);
        objectDetails.put(UserLogin.userId.getColumnName(), userDetail.getUserId());
        final String validationResult = UserDetailValidation.validateDetails(userDetail, UserDetails.class);

        if (validationResult.equals("valid")) {
            result.put("result", AUTHENTICATION_CONTROLLER.deleteDetailsById(TableName.user_login, objectDetails));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}