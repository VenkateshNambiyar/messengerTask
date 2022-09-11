package com.messenger.conversation.view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;

import com.messenger.conversation.controller.ConversationController;
import com.messenger.conversation.model.ConversationDetail;
import com.messenger.orm.Message;
import com.messenger.orm.TableName;
import com.messenger.validation.GetContactDetails;
import com.messenger.validation.GetUserDetails;
import com.messenger.validation.UserDetailValidation;

import org.json.simple.JSONObject;

/**
 * Provide implementation for MessengerMessageDetails
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageView extends ConversationController {

    private final static ConversationController CONVERSATION_CONTROLLER = new ConversationController();

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
        final String validationMessage = UserDetailValidation.validateDetails(conversationDetail,
                GetUserDetails.class);
        final Map<String, Object> userInformation = new HashMap<>();
        final Map<String, Object> result = new HashMap<>();

        conversationDetail.setMessageTime(messageTimestamp);
        userInformation.put(Message.messageTime.getColumnName(), conversationDetail.getMessageTime());
        userInformation.put(Message.messageDetails.getColumnName(), conversationDetail.getMessageContent());

        if (validationMessage.equals("valid")) {
            result.put("status", CONVERSATION_CONTROLLER.addMessage(TableName.message, userInformation));
        } else {
            result.put("status", validationMessage);
        }
        return new JSONObject(result);
    }

    /**
     * Obtain a specific user message record
     *
     * @param id represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    @Path("/messageHistory/{id}")
    @Produces("application/json")
    @GET
    public JSONObject getMessageHistory(final @PathParam("id") long id) {
        final Map<String, Object> result = new HashMap<>();
        final List<String> columnList = new ArrayList<>();
        final Map<String, Object> conditionColumnMap = new HashMap<>();
        final ConversationDetail conversationDetail = new ConversationDetail();

        conversationDetail.setContactId(id);
        final String validationResult = UserDetailValidation.validateDetails(conversationDetail,
                GetContactDetails.class);

        columnList.add(Message.messageTime.getColumnName());
        columnList.add(Message.messageDetails.getColumnName());
        conditionColumnMap.put(Message.messageId.getColumnName(), conversationDetail.getContactId());

        if (validationResult.equals("valid")) {
            result.put("result", CONVERSATION_CONTROLLER.getMessage(TableName.message, columnList,
                    conditionColumnMap));
        } else {
            result.put("result", validationResult);
        }
        return new JSONObject(result);
    }
}