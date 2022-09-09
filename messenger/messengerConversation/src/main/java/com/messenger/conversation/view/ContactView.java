package com.messenger.conversation.view;

import java.util.HashMap;
import java.util.Map;

import com.messenger.conversation.controller.ConversationController;
import com.messenger.conversation.model.ConversationDetail;
import com.messenger.conversation.model.UserContactTable;
import com.messenger.validation.AddContact;
import com.messenger.validation.UserDetailValidation;
import com.messenger.validation.getContactDetails;
import com.messenger.validation.updateMobileNumber;

import org.json.simple.JSONObject;

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * Provide implementation for MessengerContact
 *
 * @author Venkatesh N
 * @version 1.0
 */
@Path("/")
public class ContactView extends ConversationController {

    /**
     * Obtain a specific user contact record
     *
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    @Path("/userContactDetails/{contactId}")
    @Produces("application/json")
    @GET
    public JSONObject getContact(@QueryParam("contactId")final long contactId) {
        final Map<String, Object> result = new HashMap<>();
        final ConversationDetail conversationDetail = new ConversationDetail();

        conversationDetail.setContactId(contactId);
        final String validationResult = UserDetailValidation.validateUserDetail(conversationDetail,
                getContactDetails.class);

        if (validationResult.equals("valid")) {
            result.put("result", super.getUserContact(UserContactTable.primaryKey.name, UserContactTable.tableName.name,
                    contactId));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }

    /**
     * Insert a new user contact
     *
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Path("/addContact")
    @Produces("application/json")
    @POST
    public JSONObject addContact(final ConversationDetail conversationDetail) {
        final String validationMessage = UserDetailValidation.validateUserDetail(conversationDetail, AddContact.class);
        final Map<String, Object> userInformation = new HashMap<>();
        final Map<String, Object> result = new HashMap<>();

        userInformation.put("mobile_number", conversationDetail.getMobileNumber());
        userInformation.put("person_name", conversationDetail.getPersonName());

        if (validationMessage.equals("valid")) {
            result.put("status", super.addNewContact(UserContactTable.tableName.name, userInformation));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Changes a user's current mobileNumber
     *
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Path("/changeMobileNumber")
    @Produces("application/json")
    @PUT
    public JSONObject changeMobileNumber(final ConversationDetail conversationDetail) {
        final String validationMessage = UserDetailValidation.validateUserDetail(conversationDetail,
                updateMobileNumber.class);
        final Map<String, Object> result = new HashMap<>();

        if (validationMessage.equals("valid")) {
            result.put("status", super.updateMobileNumber(UserContactTable.primaryKey.name,
                    UserContactTable.tableName.name, conversationDetail));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Removes a specific user profile
     *
     * @param contactId  represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Path("/deleteContact/{contactId}")
    @Produces("application/json")
    @DELETE
    public JSONObject deleteContact(final @PathParam("contactId") long contactId) {
        final Map<String, Object> result = new HashMap<>();
        final ConversationDetail conversationDetail = new ConversationDetail();

        conversationDetail.setContactId(contactId);
        final String validationResult = UserDetailValidation.validateUserDetail(conversationDetail,
                getContactDetails.class);

        if (validationResult.equals("valid")) {
            result.put("result", super.deleteUserContact(UserContactTable.primaryKey.name,
                    UserContactTable.tableName.name, contactId));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}