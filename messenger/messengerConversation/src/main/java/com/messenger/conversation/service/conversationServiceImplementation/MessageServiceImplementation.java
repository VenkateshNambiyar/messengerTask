package com.messenger.conversation.service.conversationServiceImplementation;

import com.messenger.conversation.dao.MessageDAO;
import com.messenger.conversation.model.Conversation;
import com.messenger.conversation.service.conversationService.MessageService;

import java.util.List;

/**
 * Provide implementation for Message
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageServiceImplementation implements MessageService {

    private static final MessageDAO MESSAGE_DAO = new MessageDAO();

    /**
     * Find a particular user message record.
     *
     * @param username name of the user.
     * @return Contact of the Model
     */
    @Override
    public List<Object> getMessage(String username) {
        return MESSAGE_DAO.getMessage(username);
    }

    /**
     * Creates a new message record
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    @Override
    public boolean addMessage(final Conversation conversation) {
        return MESSAGE_DAO.addMessage(conversation);
    }
}
