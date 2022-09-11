package com.messenger.conversation.service.conversationService;

import com.messenger.orm.TableName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides the services available for message in messenger.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface MessageService {

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean addMessage(final Enum<TableName> tableName, final Map<String, Object> objectDetails);

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    Collection<Map<String, Object>> getMessage(final Enum<TableName> tableName, final List<String> columnList,
                                                      final Map<String, Object> conditionColumnName);
}