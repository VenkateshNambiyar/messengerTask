package com.messenger.conversation.view;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;

import com.messenger.conversation.controller.ConversationController;
import com.messenger.conversation.model.Conversation;
import com.messenger.conversation.validation.Implementation.ConversationValidation;
import org.json.simple.JSONObject;

/**
 * Provide implementation for MessengerMessageDetails
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageView extends ConversationController {

    /**
     * Creates a new message record.
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    @Path("/sendMessage")
    @Produces("application/json")
    @POST
    public JSONObject sendMessage(final Conversation conversation) {
        final Date date = new Date();
        final long time = date.getTime();
        final Map<String, Object> result = new HashMap<>();
        final String profileNameValidation = ConversationValidation.checkName(conversation.getProfileName());
        final String profileIdValidation = ConversationValidation.checkId(conversation.getProfileId());
        final Timestamp messageTimestamp = new Timestamp(time);

        conversation.setMessageTime(messageTimestamp);

        try {

            if (profileNameValidation.equals("Name is valid") &
                    profileIdValidation.equals("Id is valid")) {
                result.put("result", super.addMessage(conversation));
            } else {
                result.put("profileName", profileNameValidation);
                result.put("profileId", profileIdValidation);
            }
        } catch (Exception exception) {
            result.put("result", "UserName already Exists");
        }
        return new JSONObject(result);
    }

    /**
     * Find a particular user message record.
     *
     * @param profileName name of the user.
     * @return Contact of the Model
     */
    @Path("/messageHistory/{profileName}")
    @Produces("application/json")
    @GET
    public JSONObject getMessageHistory(final @PathParam("profileName") String profileName) {
        final Map<String, Object> result = new HashMap<>();
        final String validationResult = ConversationValidation.checkName(profileName);

        if (validationResult.equals("Name is valid")) {
            result.put("result", super.getMessage(profileName));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}