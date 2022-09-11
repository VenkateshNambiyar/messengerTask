package com.messenger.conversation.service.conversationService;

import com.messenger.orm.TableName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides the services available for message in messenger
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface ContactService {

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    Collection<Map<String, Object>> getUserContact(final Enum<TableName> tableName, final List<String> columnList,
                                                          final Map<String, Object> conditionColumnName);

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean addNewContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails);

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean updateMobileNumber(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                               final Map<String, Object> conditionColumn);

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean deleteUserContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails);
}