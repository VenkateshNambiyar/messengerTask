package com.messenger.conversation.dao;

import com.messenger.connectDatabase.ConnectDataBase;
import com.messenger.conversation.model.Conversation;
import com.messenger.exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * provide database connection to perform create, read, update, delete operation
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageDAO {

    private static final Connection CONNECTION = ConnectDataBase.getInstance().getConnection();

    /**
     * Creates a new message record
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    public boolean addMessage(final Conversation conversation) {
        final String insertSql = "insert into message(message_time, message_details) values(?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(insertSql)) {
            preparedStatement.setTimestamp(1, conversation.getMessageTime());
            preparedStatement.setString(2, conversation.getMessageContent());
            preparedStatement.executeUpdate();

            return storeMessageDetails(conversation);
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * Retrieve a user message details.
     *
     * @param conversation object of the Conversion model
     * @return retrieveMessageId of the user
     */
    private long retrieveMessageId(final Conversation conversation) {
        long retrieveMessageId = 0;
        final String selectSql = "select org_id from message where message_time = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(selectSql)) {
            preparedStatement.setTimestamp(1, conversation.getMessageTime());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                retrieveMessageId = resultSet.getLong("org_id");
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
        return retrieveMessageId;
    }

    /**
     * Store send message record
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    public boolean storeMessageDetails(final Conversation conversation) {
        final ContactDAO contactDAO = new ContactDAO();
        final String insertSql = "insert into user_message(user_id, message_id, contact_id) values (?, ?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(insertSql)) {
            preparedStatement.setLong(1, contactDAO.retrieveUserId(conversation));
            preparedStatement.setLong(2, retrieveMessageId(conversation));
            preparedStatement.setLong(3, conversation.getProfileId());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * Find a particular user message record.
     *
     * @param username name of the user.
     * @return Contact of the Model
     */
    public List<Object> getMessage(final String username) {
        final List<Object> messageHistory = new ArrayList<>();
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("select contact.org_id,contact.mobile_number, contact.person_name, message.message_time,");
        stringBuilder.append(" message.message_details from message inner join user_message on ");
        stringBuilder.append(" message.org_id = user_message.message_id inner join contact on ");
        stringBuilder.append(" user_message.contact_id = contact.org_id inner join user_login on");
        stringBuilder.append(" user_login.org_id = user_message.user_id");
        stringBuilder.append(" where  user_login.user_name = ? ");

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(String.valueOf(stringBuilder))) {
            preparedStatement.setString(1, username);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Map<String, Object> messageDetails = new HashMap<>();

                messageDetails.put("contactId", resultSet.getLong("org_id"));
                messageDetails.put("mobileNumber", resultSet.getLong("mobile_number"));
                messageDetails.put("personName", resultSet.getString("person_name"));
                messageDetails.put("messageTime", resultSet.getTimestamp("message_time").toString());
                messageDetails.put("messageDetails", resultSet.getString("message_details"));
                messageHistory.add(messageDetails);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
        return messageHistory;
    }
}