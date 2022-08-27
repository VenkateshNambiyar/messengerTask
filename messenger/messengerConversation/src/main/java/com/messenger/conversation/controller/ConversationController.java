package com.messenger.conversation.controller;

import com.messenger.conversation.model.Conversation;
import com.messenger.conversation.service.conversationServiceImplementation.ContactServiceImplementation;
import com.messenger.conversation.service.conversationServiceImplementation.MessageServiceImplementation;

import java.util.List;

/**
 * Provides a services for Messenger
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ConversationController {

    private static final ContactServiceImplementation CONTACT_SERVICE = new ContactServiceImplementation();
    private static final MessageServiceImplementation MESSAGE_SERVICE = new MessageServiceImplementation();

    /**
     * Find a particular user contact record.
     *
     * @param username name of the user.
     * @return Contact of the Model
     */
    public List<Object> getUserContact(final String username) {
        return CONTACT_SERVICE.getUserContact(username);
    }

    /**
     * Creates a new contact.
     *
     * @param conversation object of the Messenger.
     * @return Success or failure message
     */
    public boolean addNewContact(final Conversation conversation) {
        return CONTACT_SERVICE.addNewContact(conversation);
    }

    /**
     * Updates an existing mobileNumber for a user.
     *
     * @param conversation object of the messenger.
     * @return Success or failure message
     */
    public boolean updateMobileNumber(final Conversation conversation) {
        return CONTACT_SERVICE.updateMobileNumber(conversation);
    }

    /**
     * Deletes a particular user contact records.
     *
     * @param contactId userid of the messenger user.
     * @return Success or failure message
     */
    public boolean deleteUserContact(final long contactId) {
        return CONTACT_SERVICE.deleteUserContact(contactId);
    }

    /**
     * Creates a new message record.
     *
     * @param conversation object of the Messenger.
     * @return Success or failure message
     */
    public boolean addMessage(final Conversation conversation) {
        return MESSAGE_SERVICE.addMessage(conversation);
    }

    /**
     * Find a particular user message record.
     *
     * @param username name of the user.
     * @return Contact of the Model
     */
    public List<Object> getMessage(final String username) {
        return MESSAGE_SERVICE.getMessage(username);
    }
}