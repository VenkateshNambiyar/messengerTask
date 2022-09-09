package com.messenger.conversation.service.conversationServiceImpl;

import com.messenger.conversation.dao.MessageDAO;
import com.messenger.conversation.service.conversationService.MessageService;

import java.util.Collection;
import java.util.Map;

/**
 * Provide implementation for Message
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageServiceImpl implements MessageService {

    private static final MessageDAO MESSAGE_DAO = new MessageDAO();

    /**
     * Insert a new user message
     *
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean addMessage(final String tableName, final Map<String, Object> conversationDetail) {
        return MESSAGE_DAO.addMessage(tableName, conversationDetail);
    }

    /**
     * Obtain a specific user message record
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    @Override
    public Collection<Map<String, Object>> getMessage(final String primaryKey, final String tableName,
                                                      final long contactId) {
        return MESSAGE_DAO.getMessage(primaryKey, tableName, contactId);
    }
}