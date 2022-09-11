package com.messenger.conversation.service.conversationServiceImpl;

import com.messenger.conversation.dao.MessageDAO;
import com.messenger.conversation.service.conversationService.MessageService;
import com.messenger.orm.TableName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provide implementation for Message
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageServiceImpl implements MessageService {

    private static final MessageDAO MESSAGE_DAO = new MessageDAO();

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean addMessage(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return MESSAGE_DAO.addMessage(tableName, objectDetails);
    }

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    public Collection<Map<String, Object>> getMessage(final Enum<TableName> tableName, final List<String> columnList,
                                                      final Map<String, Object> conditionColumnName) {
        return MESSAGE_DAO.getMessage(tableName, columnList, conditionColumnName);
    }
}