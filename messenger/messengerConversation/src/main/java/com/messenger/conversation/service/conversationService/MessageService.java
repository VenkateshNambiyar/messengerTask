package com.messenger.conversation.service.conversationService;

import java.util.Collection;
import java.util.Map;

/**
 * Provides the services available for message in messenger.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface MessageService {

    /**
     * Insert a new user message
     *
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    Boolean addMessage(final String tableName, final Map<String, Object> conversationDetail);

    /**
     * Obtain a specific user message record
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    Collection<Map<String, Object>> getMessage(final String primaryKey, final String tableName, final long contactId);
}