package com.messenger.conversation.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.messenger.conversation.controller.ConversationController;
import com.messenger.conversation.model.ConversationDetail;
import com.messenger.orm.Contact;
import com.messenger.orm.TableName;
import com.messenger.validation.AddContact;
import com.messenger.validation.GetContactDetails;
import com.messenger.validation.UpdateMobileNumber;
import com.messenger.validation.UserDetailValidation;

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
public class ContactView {

    private final static ConversationController CONVERSATION_CONTROLLER = new ConversationController();

    /**
     * Obtain a specific user contact record
     *
     * @param id represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    @Path("/userContactDetails/{id}")
    @Produces("application/json")
    @GET
    public JSONObject getContact(@QueryParam("id") final long id) {
        final Map<String, Object> result = new HashMap<>();
        final List<String> columnList = new ArrayList<>();
        final Map<String, Object> conditionColumnMap = new HashMap<>();
        final ConversationDetail conversationDetail = new ConversationDetail();

        conversationDetail.setContactId(id);
        final String validationResult = UserDetailValidation.validateDetails(conversationDetail,
                GetContactDetails.class);

        columnList.add(Contact.contactId.getColumnName());
        columnList.add(Contact.mobileNumber.getColumnName());
        columnList.add(Contact.personName.getColumnName());
        conditionColumnMap.put(Contact.contactId.getColumnName(), conversationDetail.getContactId());

        if (validationResult.equals("valid")) {
            result.put("result", CONVERSATION_CONTROLLER.getUserContact(TableName.contact, columnList,
                    conditionColumnMap));
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
        final String validationMessage = UserDetailValidation.validateDetails(conversationDetail, AddContact.class);
        final Map<String, Object> userInformation = new HashMap<>();
        final Map<String, Object> result = new HashMap<>();

        userInformation.put(Contact.mobileNumber.getColumnName(), conversationDetail.getMobileNumber());
        userInformation.put(Contact.personName.getColumnName(), conversationDetail.getPersonName());

        if (validationMessage.equals("valid")) {
            result.put("status", CONVERSATION_CONTROLLER.addNewContact(TableName.contact, userInformation));
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
        final String validationMessage = UserDetailValidation.validateDetails(conversationDetail,
                UpdateMobileNumber.class);
        final Map<String, Object> result = new HashMap<>();
        final Map<String, Object> conditionColumn = new HashMap<>();
        final Map<String, Object> objectDetails = new HashMap<>();

        objectDetails.put(Contact.mobileNumber.getColumnName(), conversationDetail.getMobileNumber());
        conditionColumn.put(Contact.contactId.getColumnName(), conversationDetail.getContactId());

        if (validationMessage.equals("valid")) {
            result.put("status", CONVERSATION_CONTROLLER.updateMobileNumber(TableName.contact, objectDetails,
                    conditionColumn));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Removes a specific user profile
     *
     * @param id represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Path("/deleteContact/{id}")
    @Produces("application/json")
    @DELETE
    public JSONObject deleteContact(final @PathParam("id") long id) {
        final Map<String, Object> result = new HashMap<>();
        final Map<String, Object> objectDetails = new HashMap<>();
        final ConversationDetail conversationDetail = new ConversationDetail();

        conversationDetail.setContactId(id);
        objectDetails.put(Contact.contactId.getColumnName(), conversationDetail.getContactId());
        final String validationResult = UserDetailValidation.validateDetails(conversationDetail,
                GetContactDetails.class);

        if (validationResult.equals("valid")) {
            result.put("result", CONVERSATION_CONTROLLER.deleteUserContact(TableName.contact, objectDetails));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}