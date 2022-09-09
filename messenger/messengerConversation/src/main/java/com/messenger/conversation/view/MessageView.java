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
import com.messenger.conversation.model.ConversationDetail;
import com.messenger.conversation.model.UserContactTable;
import com.messenger.validation.GetUserDetails;
import com.messenger.validation.UserDetailValidation;
import com.messenger.validation.getContactDetails;

import org.json.simple.JSONObject;

/**
 * Provide implementation for MessengerMessageDetails
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageView extends ConversationController {

    /**
     * Insert a new user message
     *
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Path("/sendMessage")
    @Produces("application/json")
    @POST
    public JSONObject sendMessage(final ConversationDetail conversationDetail) {
        final Date date = new Date();
        final long time = date.getTime();
        final Timestamp messageTimestamp = new Timestamp(time);
        final String validationMessage = UserDetailValidation.validateUserDetail(conversationDetail,
                GetUserDetails.class);
        final Map<String, Object> userInformation = new HashMap<>();
        final Map<String, Object> result = new HashMap<>();

        conversationDetail.setMessageTime(messageTimestamp);
        userInformation.put("message_time", conversationDetail.getMessageTime());
        userInformation.put("message_details", conversationDetail.getMessageContent());

        if (validationMessage.equals("valid")) {
            result.put("status", super.addMessage(UserContactTable.tableName.name, userInformation));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Obtain a specific user message record
     *
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    @Path("/messageHistory/{contactId}")
    @Produces("application/json")
    @GET
    public JSONObject getMessageHistory(final @PathParam("contactId") long contactId) {
        final Map<String, Object> result = new HashMap<>();
        final ConversationDetail conversationDetail = new ConversationDetail();

        conversationDetail.setContactId(contactId);
        final String validationResult = UserDetailValidation.validateUserDetail(conversationDetail,
                getContactDetails.class);

        if (validationResult.equals("valid")) {
            result.put("result", super.getMessage(UserContactTable.primaryKey.name, UserContactTable.tableName.name,
                    contactId));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}