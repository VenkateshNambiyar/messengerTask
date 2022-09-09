package com.messenger.conversation.service.conversationServiceImpl;

import com.messenger.conversation.dao.ContactDAO;
import com.messenger.conversation.model.ConversationDetail;
import com.messenger.conversation.service.conversationService.ContactService;

import java.util.Collection;
import java.util.Map;

/**
 * provide implementation for Contact
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ContactServiceImpl implements ContactService {

    private static final ContactDAO CONTACT_DAO = new ContactDAO();

    /**
     * Obtain a specific user contact record
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    @Override
    public Collection<Map<String, Object>> getUserContact(final String primaryKey, final String tableName,
                                                          final long contactId) {
        return CONTACT_DAO.getUserContact(primaryKey, tableName, contactId);
    }

    /**
     * Insert a new user contact
     *
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean addNewContact(final String tableName, final Map<String, Object> conversationDetail) {
        return CONTACT_DAO.addContact(tableName, conversationDetail);
    }

    /**
     * Changes a user's current mobileNumber
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean updateMobileNumber(final String primaryKey, final String tableName,
                                      final ConversationDetail conversationDetail) {
        return CONTACT_DAO.updateMobileNumber(primaryKey, tableName, conversationDetail);
    }

    /**
     * Removes a specific user profile
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId  represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    @Override
    public Boolean deleteUserContact(final String primaryKey, final String tableName, final long contactId) {
        return CONTACT_DAO.deleteContact(primaryKey, tableName, contactId);
    }
}