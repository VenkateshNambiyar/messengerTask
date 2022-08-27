package com.messenger.conversation.view;

import java.util.HashMap;
import java.util.Map;

import com.messenger.conversation.controller.ConversationController;
import com.messenger.conversation.model.Conversation;
import com.messenger.conversation.validation.Implementation.ConversationValidation;
import org.json.simple.JSONObject;

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

/**
 * Provide implementation for MessengerContact
 *
 * @author Venkatesh N
 * @version 1.0
 */
@Path("/")
public class ContactView extends ConversationController {

    /**
     * Find a particular user contact record.
     *
     * @param personName name of the user.
     * @return Contact of the Model
     */
    @Path("/userContactDetails/{personName}")
    @Produces("application/json")
    @GET
    public JSONObject getContact(final @PathParam("personName") String personName) {
        final Map<String, Object> result = new HashMap<>();
        final String validationResult = ConversationValidation.checkName(personName);

        if (validationResult.equals("Name is valid")) {
            result.put("result", super.getUserContact(personName));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }

    /**
     * Creates a new contact
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    @Path("/addContact")
    @Produces("application/json")
    @POST
    public JSONObject addContact(final Conversation conversation) {
        final Map<String, Object> result = new HashMap<>();
        final String mobileNumberValidation = ConversationValidation.checkMobileNumber(conversation.getMobileNumber());
        final String personNameValidation = ConversationValidation.checkName(conversation.getPersonName());

        try {

            if (personNameValidation.equals("Name is valid") &
                    mobileNumberValidation.equals("MobileNumber is valid")) {
                result.put("result", super.addNewContact(conversation));
            } else {
                result.put("mobileNumber", mobileNumberValidation);
                result.put("personName", personNameValidation);
            }
        } catch (Exception exception) {
            result.put("result", "UserName already Exists");
        }
        return new JSONObject(result);
    }

    /**
     * Updates an existing mobileNumber for a user.
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    @Path("/changeMobileNumber")
    @Produces("application/json")
    @PUT
    public JSONObject changeMobileNumber(final Conversation conversation) {
        final Map<String, Object> result = new HashMap<>();
        final String mobileNumberValidation = ConversationValidation.checkMobileNumber(conversation.getMobileNumber());
        final String contactIdValidation = ConversationValidation.checkName
                (String.valueOf(conversation.getContactId()));

        try {

            if (contactIdValidation.equals("Id is valid") &
                    mobileNumberValidation.equals("MobileNumber is valid")) {
                result.put("result", super.updateMobileNumber(conversation));
            } else {
                result.put("mobileNumber", mobileNumberValidation);
                result.put("contactId", contactIdValidation);
            }
        } catch (Exception exception) {
            result.put("result", "UserName already Exists");
        }
        return new JSONObject(result);
    }

    /**
     * Deletes a particular user contact records.
     *
     * @param contactId contactId of the messenger user.
     * @return Success or failure message
     */
    @Path("/deleteContact/{contactId}")
    @Produces("application/json")
    @DELETE
    public JSONObject deleteContact(final @PathParam("contactId") long contactId) {
        final Map<String, Object> result = new HashMap<>();
        final String validationResult = ConversationValidation.checkId(contactId);

        if (validationResult.equals("Id is valid")) {
            result.put("result", super.deleteUserContact(contactId));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}