package com.messenger.conversation.service.conversationServiceImplementation;

import com.messenger.conversation.dao.ContactDAO;
import com.messenger.conversation.model.Conversation;
import com.messenger.conversation.service.conversationService.ContactService;

import java.util.List;

/**
 * provide implementation for Contact
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ContactServiceImplementation implements ContactService {

    private static final ContactDAO CONTACT_DAO = new ContactDAO();

    /**
     * Find a particular user contact record
     *
     * @param username name of the user
     * @return Contact of the Model
     */
    @Override
    public List<Object> getUserContact(final String username) {
        return CONTACT_DAO.getUserContact(username);
    }

    /**
     * Creates a new contact.
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    @Override
    public boolean addNewContact(final Conversation conversation) {
        return CONTACT_DAO.addContact(conversation);
    }

    /**
     * Updates an existing mobileNumber for a user
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    @Override
    public boolean updateMobileNumber(final Conversation conversation) {
        return CONTACT_DAO.updateMobileNumber(conversation);
    }

    /**
     * Deletes a particular userprofile
     *
     * @param contactId userid of the messenger user
     * @return Success or failure message
     */
    @Override
    public boolean deleteUserContact(final long contactId) {
        return CONTACT_DAO.deleteContact(contactId);
    }
}