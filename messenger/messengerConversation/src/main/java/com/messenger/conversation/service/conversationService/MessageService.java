package com.messenger.conversation.service.conversationService;

import com.messenger.conversation.model.Conversation;

import java.util.List;

/**
 * Provides the services available for message in messenger.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface MessageService {

    /**
     * Find a particular user message record
     *
     * @param username name of the user
     * @return message of the user Model
     */
    List<Object> getMessage(final String username);

    /**
     * Creates a new message record
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    boolean addMessage(final Conversation conversation);
}