package com.messenger.conversation.service.conversationService;

import com.messenger.conversation.model.Conversation;

import java.util.List;

/**
 * Provides the services available for contact in messenger
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface ContactService {

    /**
     * Find a particular user contact record
     *
     * @param username name of the user
     * @return Contact of the Model
     */
    List<Object> getUserContact(final String username);

    /**
     * Creates a new contact.
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    boolean addNewContact(final Conversation conversation);

    /**
     * Updates an existing mobileNumber for a user
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    boolean updateMobileNumber(final Conversation conversation);

    /**
     * Deletes a particular user contact records
     *
     * @param contactId contactId of the messenger user
     * @return Success or failure message
     */
    boolean deleteUserContact(final long contactId);
}