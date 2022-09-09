package com.messenger.conversation.dao;

import com.messenger.conversation.model.UserContactTable;
import com.messenger.orm.ORMImpl;

import java.util.Collection;
import java.util.Map;

/**
 * Create, read, update, and delete operations should be performed via a database connection
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageDAO {

    private static final ORMImpl ORM_IMPL = new ORMImpl();

    /**
     * Insert a new user message
     *
     * @param tableName  represent database table's name
     * @param conversationDetail represent a ConversationDetail model object
     * @return message of Success or Failure
     */
    public boolean addMessage(final String tableName, final Map<String, Object> conversationDetail) {
        return ORM_IMPL.insert(tableName, conversationDetail);
    }

    /**
     * Obtain a specific user message record
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param contactId represent a ConversionDetail model object
     * @return information about the specified user's userName and userId
     */
    public Collection<Map <String,Object>> getMessage (final String primaryKey, final String tableName,
                                                          final long contactId) {
        final String[] fieldName = new String[2];

        for (final String column : UserContactTable.columnField.field) {

            if (column.equals("mobile_number")) {
                fieldName[0] = column;
                fieldName[1] = primaryKey;
            }
        }
        return ORM_IMPL.getParticularUserDetails(primaryKey, tableName, fieldName, contactId);
    }
}