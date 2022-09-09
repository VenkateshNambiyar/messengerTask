package com.messenger.conversation.controller;

import com.messenger.conversation.model.ConversationDetail;
import com.messenger.conversation.service.conversationServiceImpl.ContactServiceImpl;
import com.messenger.conversation.service.conversationServiceImpl.MessageServiceImpl;

import java.util.Collection;
import java.util.Map;

/**
 * Controls the data flow into the model object and updates the view whenever data changes
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ConversationController {

    private static final ContactServiceImpl CONTACT_SERVICE = new ContactServiceImpl();
    private static final MessageServiceImpl MESSAGE_SERVICE = new MessageServiceImpl();

    /**
     * Obtain a specific user contact record
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    public Collection<Map<String, Object>> getUserContact(final String primaryKey, final String tableName,
                                                          final long contactId) {
        return CONTACT_SERVICE.getUserContact(primaryKey, tableName, contactId);
    }

    /**
     * Insert a new user contact
     *
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    public Boolean addNewContact(final String tableName, final Map<String, Object> conversationDetail) {
        return CONTACT_SERVICE.addNewContact(tableName, conversationDetail);
    }

    /**
     * Changes a user's current mobileNumber
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    public Boolean updateMobileNumber(final String primaryKey, final String tableName,
                                      final ConversationDetail conversationDetail) {
        return CONTACT_SERVICE.updateMobileNumber(primaryKey, tableName, conversationDetail);
    }

    /**
     * Removes a specific user profile
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId  represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    public Boolean deleteUserContact(final String primaryKey, final String tableName, final long contactId) {
        return CONTACT_SERVICE.deleteUserContact(primaryKey, tableName, contactId);
    }

    /**
     * Insert a new user message
     *
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    public Boolean addMessage(final String tableName, final Map<String, Object> conversationDetail) {
        return MESSAGE_SERVICE.addMessage(tableName, conversationDetail);
    }

    /**
     * Obtain a specific user message record
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    public Collection<Map<String, Object>> getMessage(final String primaryKey, final String tableName,
                                                      final long contactId) {
        return MESSAGE_SERVICE.getMessage(primaryKey, tableName, contactId);
    }
}