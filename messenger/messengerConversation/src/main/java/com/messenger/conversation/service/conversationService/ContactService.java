package com.messenger.conversation.service.conversationService;

import com.messenger.conversation.model.ConversationDetail;

import java.util.Collection;
import java.util.Map;

/**
 * Provides the services available for message in messenger
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface ContactService {

    /**
     * Obtain a specific user contact record
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    Collection<Map<String, Object>> getUserContact(final String primaryKey, final String tableName,
                                                   final long contactId);

    /**
     * Insert a new user contact
     *
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    Boolean addNewContact(final String tableName, final Map<String, Object> conversationDetail);

    /**
     * Changes a user's current mobileNumber
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    Boolean updateMobileNumber(final String primaryKey, final String tableName,
                               final ConversationDetail conversationDetail);

    /**
     * Removes a specific user profile
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId  represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    Boolean deleteUserContact(final String primaryKey, final String tableName, final long contactId);
}